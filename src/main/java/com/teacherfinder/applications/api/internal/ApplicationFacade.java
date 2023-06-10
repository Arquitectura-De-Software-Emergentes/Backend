package com.teacherfinder.applications.api.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.teacherfinder.applications.domain.model.valueObjects.ApplicationId;
import com.teacherfinder.applications.domain.service.ApplicationService;

@Component
public class ApplicationFacade {

    @Autowired
    ApplicationService service;

    public ResponseEntity<String> apply(Long jobOfferId, Long ApplicantId){
        return service.apply(new ApplicationId(ApplicantId,jobOfferId));
    }
}
