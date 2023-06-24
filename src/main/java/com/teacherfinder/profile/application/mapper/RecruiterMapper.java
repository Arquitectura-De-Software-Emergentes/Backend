package com.teacherfinder.profile.application.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.teacherfinder.profile.application.dto.CreateRecruiterResource;
import com.teacherfinder.profile.application.dto.RecruiterResource;
import com.teacherfinder.profile.domain.model.aggregate.Recruiter;
import com.teacherfinder.shared.mapping.EnhancedModelMapper;

public class RecruiterMapper {
    @Autowired
    EnhancedModelMapper mapper;

    public Recruiter toModel(CreateRecruiterResource recruiterResource){
        return mapper.map(recruiterResource, Recruiter.class);
    }

    public RecruiterResource toResource(Recruiter model){
        return mapper.map(model, RecruiterResource.class);
    }
}
