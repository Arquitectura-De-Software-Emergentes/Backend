package com.teacherfinder.offers.domain.service;

import com.teacherfinder.offers.domain.model.aggregate.JobOffer;
import com.teacherfinder.offers.domain.model.entity.PositionProfile;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface JobOfferService {
    JobOffer createJobOffer(JobOffer request);
    ResponseEntity<String> apply(Long jobOfferId, Long applicantId);
    JobOffer getJobOfferById(Long jobOfferId);
    List<JobOffer> getAllJobOffer();
    List<JobOffer> getAllJobOfferByRecruiterId(Long recruiterId);
    PositionProfile updatePositionProfile(Long positionProfileId, PositionProfile request);
    ResponseEntity<String> enable(Long jobOfferId);
    ResponseEntity<String> disable(Long jobOfferId);
}
