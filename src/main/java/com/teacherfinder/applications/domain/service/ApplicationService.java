package com.teacherfinder.applications.domain.service;

import java.util.List;

import com.teacherfinder.applications.domain.model.entity.JobOffer;
import org.springframework.http.ResponseEntity;

import com.teacherfinder.applications.domain.model.aggregate.Application;

public interface ApplicationService {
    ResponseEntity<String> apply(Long jobOfferId, Long applicantId);
    List<Application> getApplicationsByOfferId(Long offerId);
    List<JobOffer> getApplicationsByApplicantId(Long applicantId);
}
