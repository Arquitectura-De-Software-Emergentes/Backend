package com.teacherfinder.profile.application.service;

import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.teacherfinder.profile.domain.model.aggregate.ApplicantProfile;
import com.teacherfinder.profile.domain.repository.ApplicantProfileRepository;
import org.springframework.stereotype.Service;

import com.teacherfinder.profile.domain.model.aggregate.Applicant;
import com.teacherfinder.profile.domain.model.valueObjects.CurriculumVitae;
import com.teacherfinder.profile.domain.repository.ApplicantRepository;
import com.teacherfinder.profile.domain.service.ApplicantService;
import com.teacherfinder.shared.exception.ResourceNotFoundException;
import com.teacherfinder.shared.exception.ResourceValidationException;

@Service
public class ApplicantServiceImpl implements ApplicantService {

    private static final String APPLICANT = "applicant";
    private static final String PROFILE = "profile";
    private final ApplicantRepository applicantRepository;
    private final ApplicantProfileRepository profileRepository;
    private final Validator validator;

    

    public ApplicantServiceImpl(ApplicantRepository applicantRepository, ApplicantProfileRepository profileRepository,
            Validator validator) {
        this.applicantRepository = applicantRepository;
        this.validator = validator;
        this.profileRepository = profileRepository;
    }

    @Override
    @Transactional
    public Applicant create(Applicant applicant) {
        Set<ConstraintViolation<Applicant>> violations = validator.validate(applicant);

        if (!violations.isEmpty())
            throw new ResourceValidationException(APPLICANT, violations);

        applicant = applicantRepository.save(applicant);

        createProfile(applicant);

        return applicant;
    }

    @Override
    public CurriculumVitae uploadCV(Long applicantId, CurriculumVitae cv) {

        Applicant applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new ResourceNotFoundException(APPLICANT, applicantId));

        applicant.setCv(cv);

        return applicantRepository.save(applicant).getCv();
    }

    @Override
    public CurriculumVitae getCv(Long applicantId) {
        Applicant currentApplicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new ResourceNotFoundException(APPLICANT, applicantId));

        if (currentApplicant.getCv() == null) 
            throw new ResourceNotFoundException("The CV of the User with id " + applicantId + " not found");

        return currentApplicant.getCv();
    }

    @Override
    public ApplicantProfile updateProfile(ApplicantProfile profile) {
        Set<ConstraintViolation<ApplicantProfile>> violations = validator.validate(profile);

        if (!violations.isEmpty())
            throw new ResourceValidationException(PROFILE, violations);
        return profileRepository.save(profile);
    }

    private void createProfile(Applicant applicant){
        profileRepository.save(new ApplicantProfile().withApplicant(applicant));
    }

}
