package com.teacherfinder.offers.domain.model.services;

import com.teacherfinder.offers.domain.model.aggregate.JobOffer;
import com.teacherfinder.offers.domain.model.aggregate.PositionProfile;
import org.springframework.stereotype.Service;


public interface JobOfferService {
    JobOffer createJobOffer(JobOffer jobOffer);
    JobOffer getJobOfferById(Long id);
    PositionProfile createPositionProfile(PositionProfile positionProfile);

    PositionProfile getPositionProfileById(Long id);

    boolean ifExistsPositionProfile(Long id);
}
