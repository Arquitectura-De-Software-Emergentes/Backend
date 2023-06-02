package com.teacherfinder.profile.application.mapper;

import com.teacherfinder.profile.application.dto.ApplicantProfileResource;
import com.teacherfinder.profile.application.dto.UpdateApplicantProfileResource;
import com.teacherfinder.profile.domain.model.entity.ApplicantProfile;
import com.teacherfinder.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class ApplicantProfileMapper implements Serializable{

    @Autowired
    EnhancedModelMapper mapper;

    public ApplicantProfile toModel(UpdateApplicantProfileResource dto) {
            return mapper.map(dto, ApplicantProfile.class);
    }
    public ApplicantProfileResource toResource(ApplicantProfile model) {
            return mapper.map(model, ApplicantProfileResource.class);
    }
}
