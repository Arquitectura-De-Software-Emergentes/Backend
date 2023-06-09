package com.teacherfinder.applications.application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.teacherfinder.applications.application.mapper.ApplicationMapper;
import com.teacherfinder.applications.domain.model.entity.ApplicationJobExperienceInformation;
import com.teacherfinder.applications.domain.model.entity.JobOffer;
import com.teacherfinder.applications.domain.model.valueObjects.ApplicationId;

import com.teacherfinder.offers.api.internal.JobOfferFacade;
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

    private static final String DEFAULT_STATUS = "postulate";
    private static final String APPLICATION = "application";

    private final ApplicationApplicantProfileMapper profileMapper;
    private final ApplicationMapper mapper;
    private final ApplicationRepository applicationRepository;
    private final ApplicationApplicantProfileRepository profileRepository;
    private final ApplicationJobExperienceInformationRepository experienceRepository;
    private final ApplicationFactory applicationFactory;
    private final ProfileFacade profileFacade;
    private final JobOfferFacade jobOfferFacade;
    private final Validator validator;

    public ApplicationServiceImpl(ApplicationApplicantProfileMapper profileMapper,
                                  ApplicationMapper mapper, ApplicationRepository applicationRepository, ApplicationApplicantProfileRepository profileRepository,
                                  ApplicationJobExperienceInformationRepository experienceRepository, ApplicationFactory applicationFactory,
                                  ProfileFacade profileFacade, JobOfferFacade jobOfferFacade, Validator validator) {
        this.profileMapper = profileMapper;
        this.mapper = mapper;
        this.applicationRepository = applicationRepository;
        this.profileRepository = profileRepository;
        this.experienceRepository = experienceRepository;
        this.applicationFactory = applicationFactory;
        this.profileFacade = profileFacade;
        this.jobOfferFacade = jobOfferFacade;
        this.validator = validator;
    }

    @Override
    @Transactional
    public ResponseEntity<String> apply(Long jobOfferId, Long applicantId) {

        ApplicationId applicationId = new ApplicationId(applicantId, jobOfferId);

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

    @Override
    public List<JobOffer> getApplicationsByApplicantId(Long applicantId) {
        List<JobOffer> offers = new ArrayList<>();

        var applications = applicationRepository.findByApplicationIdApplicantId(applicantId);

        for (Application application: applications) {
            var offer = jobOfferFacade.getJobOfferById(application.getApplicationId().getJobOfferId());
            offers.add(mapper.jobOfferFacadeToModel(offer));
        }
        return offers;
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
