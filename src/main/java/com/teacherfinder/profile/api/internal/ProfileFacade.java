package com.teacherfinder.profile.api.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.teacherfinder.profile.application.dto.ApplicantProfileResource;
import com.teacherfinder.profile.application.mapper.ApplicantProfileMapper;
import com.teacherfinder.profile.domain.service.ApplicantService;

@Component
public class ProfileFacade {
 
    @Autowired
    ApplicantService service;

    @Autowired
    ApplicantProfileMapper profileMapper;

    public ApplicantProfileResource getApplicantProfile(Long applicantId){
        return profileMapper.toResource(service.getApplicantProfile(applicantId));
    }
}
