package com.teacherfinder.applications.application.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.teacherfinder.applications.domain.model.entity.ApplicationJobExperienceInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teacherfinder.applications.application.dto.ApplyResource;
import com.teacherfinder.applications.application.mapper.ApplicationApplicantProfileMapper;
import com.teacherfinder.applications.domain.factory.ApplicationFactory;
import com.teacherfinder.applications.domain.model.aggregate.Application;
import com.teacherfinder.applications.domain.model.entity.ApplicationApplicantProfile;
import com.teacherfinder.applications.domain.repository.ApplicationApplicantProfileRepository;
import com.teacherfinder.applications.domain.repository.ApplicationJobExperienceInformationRepository;
import com.teacherfinder.applications.domain.repository.ApplicationRepository;
import com.teacherfinder.applications.domain.service.ApplicationService;
import com.teacherfinder.profile.api.internal.ProfileFacade;
import com.teacherfinder.shared.exception.ResourceValidationException;

@Service
public class ApplicationServiceImpl implements ApplicationService{

    @Autowired
    ApplicationApplicantProfileMapper profileMapper;

    private static final String DEFAULT_STATUS = "postulate";
    private static final String APPLICATION = "application";
    private final ApplicationRepository applicationRepository;
    private final ApplicationApplicantProfileRepository profileRepository;
    private final ApplicationJobExperienceInformationRepository experienceRepository;
    private final ApplicationFactory applicationFactory;
    private final ProfileFacade profileFacade;
    private final Validator validator;

    public ApplicationServiceImpl(ApplicationApplicantProfileMapper profileMapper,
            ApplicationRepository applicationRepository, ApplicationApplicantProfileRepository profileRepository,
            ApplicationJobExperienceInformationRepository experienceRepository, ApplicationFactory applicationFactory,
            ProfileFacade profileFacade, Validator validator) {
        this.profileMapper = profileMapper;
        this.applicationRepository = applicationRepository;
        this.profileRepository = profileRepository;
        this.experienceRepository = experienceRepository;
        this.applicationFactory = applicationFactory;
        this.profileFacade = profileFacade;
        this.validator = validator;
    }

    @Override
    @Transactional
    public Application apply(ApplyResource applyResource) {

        validateApplication(applyResource);

        ApplicationApplicantProfile profile = profileMapper.toApplicationApplicantProfile(profileFacade.getApplicantProfile(applyResource.getApplicantId()));

        profile = saveProfile(profile);
        saveJobExperience(profile.getJobExperienceInformations(), profile);
        
        Application application = applicationFactory.createApplication(applyResource.getApplicantId(), applyResource.getJobOfferId(), DEFAULT_STATUS, profile);

        return applicationRepository.save(application);

    }

    private void validateApplication(ApplyResource applyResource){
        Set<ConstraintViolation<ApplyResource>> violations = validator.validate(applyResource);

        if (!violations.isEmpty())
            throw new ResourceValidationException(APPLICATION, violations);
    }

    private ApplicationApplicantProfile saveProfile(ApplicationApplicantProfile profile){
        return profileRepository.save(profile);
    }

    private void saveJobExperience(List<ApplicationJobExperienceInformation> experiences, ApplicationApplicantProfile profile){

        for (ApplicationJobExperienceInformation experience : experiences) {
            experience.setApplicantProfile(profile);
            experienceRepository.save(experience);
        }

    }
    
}
