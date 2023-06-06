package com.teacherfinder.offers.domain.model.services;

import com.teacherfinder.offers.domain.model.aggregate.JobOffer;
import com.teacherfinder.offers.domain.model.aggregate.PositionProfile;

public interface JobOfferService {
    JobOffer createJobOffer(Long recruiterId , JobOffer request);
    JobOffer getJobOfferById(Long id);
    PositionProfile createPositionProfile(PositionProfile positionProfile);
    PositionProfile createOrRetrievePositionProfile(PositionProfile positionProfile);

    boolean ifExistsPositionProfile(Long id);
}
