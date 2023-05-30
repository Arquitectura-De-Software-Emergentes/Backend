package com.teacherfinder.security.application;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.stereotype.Service;

import com.teacherfinder.security.domain.model.aggregate.Applicant;
import com.teacherfinder.security.domain.model.valueObjects.CurriculumVitae;
import com.teacherfinder.security.domain.repository.ApplicantRepository;
import com.teacherfinder.security.domain.service.ApplicantService;
import com.teacherfinder.shared.exception.ResourceNotFoundException;
import com.teacherfinder.shared.exception.ResourceValidationException;

@Service
public class ApplicantServiceImpl implements ApplicantService {

    private static final String ENTITY = "applicant";
    private final ApplicantRepository applicantRepository;
    private final Validator validator;

    public ApplicantServiceImpl(ApplicantRepository applicantRepository, Validator validator) {
        this.applicantRepository = applicantRepository;
        this.validator = validator;
    }

    @Override
    public Applicant create(Applicant applicant) {
        Set<ConstraintViolation<Applicant>> violations = validator.validate(applicant);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return applicantRepository.save(applicant);
    }

    @Override
    public void addCV(Long applicantId, CurriculumVitae cv) {

        applicantRepository.findById(applicantId).map(
                applicant -> applicantRepository.save(applicant
                        .withCv(cv)))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, applicantId));
    }

    @Override
    public Applicant getCv(Long applicantId) {
        return applicantRepository.findById(applicantId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, applicantId));
    }

}
