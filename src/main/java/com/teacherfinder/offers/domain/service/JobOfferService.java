package com.teacherfinder.offers.domain.service;

import com.teacherfinder.offers.domain.model.aggregate.JobOffer;
import com.teacherfinder.offers.domain.model.entity.PositionProfile;

import java.util.List;

public interface JobOfferService {
    JobOffer createJobOffer(JobOffer request);
    JobOffer getJobOfferById(Long id);
    List<JobOffer> getAllJobOffer();
    List<JobOffer> getAllJobOfferByRecruiterId(Long recruiterId);

    PositionProfile updatePositionProfile(Long positionProfileId, PositionProfile request);
}
