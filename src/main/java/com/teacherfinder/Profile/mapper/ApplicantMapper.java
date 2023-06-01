package com.teacherfinder.Profile.mapper;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.teacherfinder.Profile.dtos.ApplicantResource;
import com.teacherfinder.Profile.dtos.CreateApplicantResource;
import com.teacherfinder.Profile.domain.model.aggregate.Applicant;
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
