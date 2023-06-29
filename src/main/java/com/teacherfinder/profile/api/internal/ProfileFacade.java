package com.teacherfinder.profile.api.internal;

import com.teacherfinder.profile.domain.service.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.teacherfinder.profile.application.dto.ApplicantProfileResource;
import com.teacherfinder.profile.application.mapper.ApplicantProfileMapper;
import com.teacherfinder.profile.domain.service.ApplicantService;

@Component
public class ProfileFacade {
 
    @Autowired
    ApplicantService applicantService;

    @Autowired
    RecruiterService recruiterService;

    @Autowired
    ApplicantProfileMapper profileMapper;

    public ApplicantProfileResource getApplicantProfile(Long applicantId){
        return profileMapper.toResource(applicantService.getApplicantProfile(applicantId));
    }

    public Boolean recruiterExist(Long recruiterId){
        return recruiterService.exist(recruiterId);
    }

    public Boolean applicantExist(Long applicantExist){
        return applicantService.exist(applicantExist);
    }


}
