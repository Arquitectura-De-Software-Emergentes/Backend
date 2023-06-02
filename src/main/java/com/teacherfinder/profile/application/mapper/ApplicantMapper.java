package com.teacherfinder.profile.application.mapper;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.teacherfinder.profile.application.dto.ApplicantResource;
import com.teacherfinder.profile.application.dto.CreateApplicantResource;
import com.teacherfinder.profile.domain.model.aggregate.Applicant;
import com.teacherfinder.shared.mapping.EnhancedModelMapper;

public class ApplicantMapper implements Serializable{
    
    @Autowired
    EnhancedModelMapper mapper;

    public Applicant toModel(CreateApplicantResource dto) {
        return mapper.map(dto, Applicant.class);
    }

    public ApplicantResource toResource(Applicant model) {
        return mapper.map(model, ApplicantResource.class);
    }
}
