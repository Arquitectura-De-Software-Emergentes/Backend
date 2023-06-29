package com.teacherfinder.offers.application.service;

import com.teacherfinder.assessment.api.internal.AssessmentFacade;
import com.teacherfinder.offers.domain.factory.PositionProfileFactory;
import com.teacherfinder.offers.domain.model.Enum.Availability;
import com.teacherfinder.offers.domain.model.aggregate.JobOffer;
import com.teacherfinder.offers.domain.model.entity.PositionProfile;
import com.teacherfinder.offers.domain.repository.JobOfferRepository;
import com.teacherfinder.offers.domain.repository.PositionProfileRepository;
import com.teacherfinder.offers.domain.service.JobOfferService;
import com.teacherfinder.profile.api.internal.ProfileFacade;
import com.teacherfinder.shared.exception.ResourceNotFoundException;
import com.teacherfinder.shared.exception.ResourceValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class JobOfferServiceImpl implements JobOfferService {
    private static final String JOB_OFFER = "JobOffer";
    private static final String POSITION_PROFILE = "PositionProfile";
    private final JobOfferRepository jobOfferRepository;
    private final PositionProfileRepository positionProfileRepository;
    private final Validator validator;
    private final PositionProfileFactory profileFactory;
    private final AssessmentFacade assessmentFacade;
    private final ProfileFacade profileFacade;

    public JobOfferServiceImpl(JobOfferRepository jobOfferRepository,
                               PositionProfileRepository positionProfileRepository,
                               Validator validator, PositionProfileFactory profileFactory,
                               AssessmentFacade assessmentFacade, ProfileFacade profileFacade) {
        this.jobOfferRepository = jobOfferRepository;
        this.positionProfileRepository = positionProfileRepository;
        this.validator = validator;
        this.profileFactory = profileFactory;
        this.assessmentFacade = assessmentFacade;
        this.profileFacade = profileFacade;
    }

    @Transactional
    @Override
    public JobOffer createJobOffer(JobOffer request) {
        Set<ConstraintViolation<JobOffer>> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            throw new ResourceValidationException(JOB_OFFER, violations);
        }

        if(!profileFacade.recruiterExist(request.getRecruiterId()))
            throw new ResourceNotFoundException("Recruiter", request.getRecruiterId());

        PositionProfile positionProfile = generateEmptyPositionProfile();

        var jobOffer = jobOfferRepository.save(request.withPositionProfile(positionProfile));

        assessmentFacade.createGenerateAssement(jobOffer.getId(),jobOffer.getInitialDate(),jobOffer.getEndDate());

        return jobOffer;
    }

    @Override
    public JobOffer getJobOfferById(Long jobOfferId) {
        return jobOfferRepository.findById(jobOfferId)
                .orElseThrow(() -> new ResourceNotFoundException(JOB_OFFER, jobOfferId));
    }

    @Override
    public List<JobOffer> getAllJobOffer() {
        return jobOfferRepository.findAll();
    }

    @Override
    public List<JobOffer> getAllJobOfferByRecruiterId(Long recruiterId) {
        if(!profileFacade.recruiterExist(recruiterId))
            throw new ResourceNotFoundException("Recruiter", recruiterId);
        return jobOfferRepository.findByRecruiterId(recruiterId);
    }

    @Override
    public PositionProfile updatePositionProfile(Long positionProfileId, PositionProfile request) {
        return positionProfileRepository.findById(positionProfileId).map(
                profile -> positionProfileRepository.save(profile
                        .withName(request.getName())
                        .withCourse(request.getCourse())
                        .withExperience(request.getExperience())
                        .withModality(request.getModality())
                        .withType(request.getType())))
                .orElseThrow(() -> new ResourceNotFoundException(POSITION_PROFILE, positionProfileId));
    }

    private PositionProfile generateEmptyPositionProfile() {
        return positionProfileRepository.save(profileFactory.createPositionProfile());
    }

    @Override
    public ResponseEntity<String> enable(Long jobOfferId) {

        jobOfferRepository.findById(jobOfferId).map(
                offer -> jobOfferRepository.save(offer
                        .withAvailability(Availability.AVAILABLE)))
                .orElseThrow(() -> new ResourceNotFoundException(JOB_OFFER, jobOfferId));

        return new ResponseEntity<String>("The offer has been enabled", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> disable(Long jobOfferId) {
        jobOfferRepository.findById(jobOfferId).map(
                offer -> jobOfferRepository.save(offer
                        .withAvailability(Availability.UNAVAILABLE)))
                .orElseThrow(() -> new ResourceNotFoundException(JOB_OFFER, jobOfferId));

        return new ResponseEntity<String>("The offer has been disabled", HttpStatus.OK);
    }
}
