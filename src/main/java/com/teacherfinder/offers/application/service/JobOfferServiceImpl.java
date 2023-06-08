package com.teacherfinder.offers.application.service;

import com.teacherfinder.offers.domain.factory.PositionProfileFactory;
import com.teacherfinder.offers.domain.model.aggregate.JobOffer;
import com.teacherfinder.offers.domain.model.entity.PositionProfile;
import com.teacherfinder.offers.domain.repository.JobOfferRepository;
import com.teacherfinder.offers.domain.repository.PositionProfileRepository;
import com.teacherfinder.offers.domain.service.JobOfferService;
import com.teacherfinder.shared.exception.ResourceNotFoundException;
import com.teacherfinder.shared.exception.ResourceValidationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class JobOfferServiceImpl implements JobOfferService {
    private static final String JOB_OFFER  = "JobOffer";
    private static final String POSITION_PROFILE = "PositionProfile";
    private final JobOfferRepository jobOfferRepository;
    private final PositionProfileRepository positionProfileRepository;
    private final Validator validator ;
    private final PositionProfileFactory profileFactory;

    public JobOfferServiceImpl(JobOfferRepository jobOfferRepository, PositionProfileRepository positionProfileRepository, Validator validator, PositionProfileFactory profileFactory) {
        this.jobOfferRepository = jobOfferRepository;
        this.positionProfileRepository = positionProfileRepository;
        this.validator = validator;
        this.profileFactory = profileFactory;
    }

    @Transactional
    @Override
    public JobOffer createJobOffer(JobOffer request) {
        Set<ConstraintViolation<JobOffer>> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            throw new ResourceValidationException(JOB_OFFER, violations);
        }

        PositionProfile positionProfile = generateEmptyPositionProfile();

        return jobOfferRepository.save(request.withPositionProfile(positionProfile));
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
        return jobOfferRepository.findByRecruiterId(recruiterId);
    }

    @Override
    public PositionProfile updatePositionProfile(Long positionProfileId, PositionProfile request) {
        return null;
    }


    private PositionProfile generateEmptyPositionProfile(){
        return positionProfileRepository.save(profileFactory.createPositionProfile());
    }

}
