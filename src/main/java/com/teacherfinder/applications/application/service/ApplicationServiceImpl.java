package com.teacherfinder.applications.application.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.teacherfinder.applications.domain.model.entity.ApplicationJobExperienceInformation;
import com.teacherfinder.applications.domain.model.valueObjects.ApplicationId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
    public ResponseEntity<String> apply(ApplicationId applicationId) {

        validateApplication(applicationId);

        ApplicationApplicantProfile profile = cloneApplicantProfile(applicationId.getApplicantId());
        
        Application application = applicationFactory.createApplication(applicationId,DEFAULT_STATUS, profile);

        applicationRepository.save(application);

        return new ResponseEntity<String>("successful application",HttpStatus.CREATED);
    }

    @Override
    public List<Application> getApplicationsByOfferId(Long offerId) {
        return applicationRepository.findByApplicationIdJobOfferId(offerId);
    }

    private ApplicationApplicantProfile cloneApplicantProfile(Long applicantId){
        ApplicationApplicantProfile profile = profileMapper.toApplicationApplicantProfile(profileFacade.getApplicantProfile(applicantId));

        profile = saveProfile(profile);
        saveJobExperience(profile.getJobExperienceInformations(), profile);

        return profile;
    }

    private void validateApplication(ApplicationId applicationId){
        Set<ConstraintViolation<ApplicationId>> violations = validator.validate(applicationId);

        if (!violations.isEmpty())
            throw new ResourceValidationException(APPLICATION, violations);

        boolean exist = applicationRepository.existsById(applicationId);

        if (exist)
            throw new ResourceValidationException("You have already applied for this offer");
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
