package com.teacherfinder.applications.domain.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.teacherfinder.applications.domain.model.aggregate.Application;
import com.teacherfinder.applications.domain.model.valueObjects.ApplicationId;

public interface ApplicationService {
    ResponseEntity<String> apply(ApplicationId applicationId);
    List<Application> getApplicationsByOfferId(Long offerId);
}
