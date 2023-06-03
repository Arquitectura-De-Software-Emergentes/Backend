package com.teacherfinder.applications.application.service;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.stereotype.Service;

import com.teacherfinder.applications.application.dto.ApplyResource;
import com.teacherfinder.applications.domain.factory.ApplicationFactory;
import com.teacherfinder.applications.domain.model.aggregate.Application;
import com.teacherfinder.applications.domain.model.valueObjects.ApplicantId;
import com.teacherfinder.applications.domain.model.valueObjects.JobOfferId;
import com.teacherfinder.applications.domain.repository.ApplicationRepository;
import com.teacherfinder.applications.domain.service.ApplicationService;
import com.teacherfinder.shared.exception.ResourceValidationException;

@Service
public class ApplicationServiceImpl implements ApplicationService{

    private static final String DEFAULT_STATUS = "postulate";
    private static final String APPLICATION = "application";
    private final ApplicationRepository applicationRepository;
    private final ApplicationFactory applicationFactory;
    private final Validator validator;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, ApplicationFactory applicationFactory,Validator validator) {
        this.applicationRepository = applicationRepository;
        this.applicationFactory = applicationFactory;
        this.validator = validator;
    }

    @Override
    public Application apply(ApplyResource applyResource) {

        Set<ConstraintViolation<ApplyResource>> violations = validator.validate(applyResource);

        if (!violations.isEmpty())
            throw new ResourceValidationException(APPLICATION, violations);

        ApplicantId applicantId = new ApplicantId(applyResource.getApplicantId());
        JobOfferId jobOfferId = new JobOfferId(applyResource.getJobOfferId());
        
        Application application = applicationFactory.createApplication(applicantId, jobOfferId, DEFAULT_STATUS, null);

        return applicationRepository.save(application);

    }
    
}
