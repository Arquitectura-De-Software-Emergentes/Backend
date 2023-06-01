package com.teacherfinder.Profile.application;

import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.stereotype.Service;

import com.teacherfinder.Profile.domain.model.aggregate.Applicant;
import com.teacherfinder.Profile.domain.model.valueObjects.CurriculumVitae;
import com.teacherfinder.Profile.domain.repository.ApplicantRepository;
import com.teacherfinder.Profile.domain.service.ApplicantProfileService;
import com.teacherfinder.Profile.domain.service.ApplicantService;
import com.teacherfinder.shared.exception.ResourceNotFoundException;
import com.teacherfinder.shared.exception.ResourceValidationException;

@Service
public class ApplicantServiceImpl implements ApplicantService {

    private static final String ENTITY = "applicant";
    private final ApplicantRepository applicantRepository;
    private final ApplicantProfileService profileService;
    private final Validator validator;

    

    public ApplicantServiceImpl(ApplicantRepository applicantRepository, ApplicantProfileService profileService,
            Validator validator) {
        this.applicantRepository = applicantRepository;
        this.profileService = profileService;
        this.validator = validator;
    }

    @Override
    @Transactional
    public Applicant create(Applicant applicant) {
        Set<ConstraintViolation<Applicant>> violations = validator.validate(applicant);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        applicant = applicantRepository.save(applicant);

        profileService.createProfile(applicant);

        return applicant;
    }

    @Override
    public CurriculumVitae uploadCV(Long applicantId, CurriculumVitae cv) {

        Applicant applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, applicantId));

        applicant.setCv(cv);

        return applicantRepository.save(applicant).getCv();
    }

    @Override
    public CurriculumVitae getCv(Long applicantId) {
        Applicant currentApplicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, applicantId));

        if (currentApplicant.getCv() == null) 
            throw new ResourceNotFoundException("The CV of the User with id " + applicantId + " not found");

        return currentApplicant.getCv();
    }

}
