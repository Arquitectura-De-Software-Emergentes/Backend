package com.teacherfinder.offers.application.service;

import com.teacherfinder.offers.domain.model.aggregate.JobOffer;
import com.teacherfinder.offers.domain.model.aggregate.PositionProfile;
import com.teacherfinder.offers.domain.model.repository.JobOfferRepository;
import com.teacherfinder.offers.domain.model.repository.PositionProfileRepository;
import com.teacherfinder.offers.domain.model.services.JobOfferService;
import com.teacherfinder.offers.domain.model.valueObjects.RecruiterId;
import com.teacherfinder.shared.exception.ResourceNotFoundException;
import com.teacherfinder.shared.exception.ResourceValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class JobOfferServiceImpl implements JobOfferService {
    private static final String JOB_OFFER  = "JobOffer";
    private static final String POSITION_PROFILE = "PositionProfile";


    @Autowired
    private  JobOfferRepository jobOfferRepository;
    @Autowired
    PositionProfileRepository positionProfileRepository;
    @Autowired
    private Validator validator ;

    public JobOffer createJobOffer(Long recruiterId, JobOffer request) {
        Set<ConstraintViolation<JobOffer>> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            throw new ResourceValidationException(JOB_OFFER, violations);
        }

        PositionProfile positionProfile = request.getPositionProfile() != null ? this.createOrRetrievePositionProfile(request.getPositionProfile()) : null;

        JobOffer jobOffer = request.withPositionProfile(positionProfile)
                .withRecruiterId(new RecruiterId(recruiterId));

        return jobOfferRepository.save(jobOffer);
    }

    @Override
    public JobOffer getJobOfferById(Long jobOfferId) {
        return jobOfferRepository.findById(jobOfferId)
                .orElseThrow(() -> new ResourceNotFoundException(JOB_OFFER, jobOfferId));
    }

    @Override
    public PositionProfile createPositionProfile(PositionProfile request) {
        Set<ConstraintViolation<PositionProfile>> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            return null;
        }
        return this.positionProfileRepository.save(request);
    }

    @Override
    public PositionProfile createOrRetrievePositionProfile(PositionProfile request) {
       PositionProfile positionProfile = positionProfileRepository.findByName(request.getName());
        if( positionProfile == null) {
          return this.createPositionProfile(request);
        }
            return positionProfile;
    }

    @Override
    public boolean ifExistsPositionProfile(Long id) {
        return positionProfileRepository.existsById(id);
    }

}
