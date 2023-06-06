package com.teacherfinder.applications.application.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.teacherfinder.applications.domain.model.entity.ApplicationApplicantProfile;
import com.teacherfinder.profile.application.dto.ApplicantProfileResource;
import com.teacherfinder.shared.mapping.EnhancedModelMapper;

public class ApplicationApplicantProfileMapper {
    @Autowired
    EnhancedModelMapper mapper;

    public ApplicationApplicantProfile toApplicationApplicantProfile(ApplicantProfileResource model){
        return mapper.map(model, ApplicationApplicantProfile.class);
    }
}
