package com.teacherfinder.profile.application.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.teacherfinder.profile.application.dto.CreateRecruiterResource;
import com.teacherfinder.profile.application.dto.RecruiterResource;
import com.teacherfinder.profile.domain.model.aggregate.User;
import com.teacherfinder.shared.mapping.EnhancedModelMapper;

public class RecruiterMapper {
    @Autowired
    EnhancedModelMapper mapper;

    public User toModel(CreateRecruiterResource recruiterResource){
        return mapper.map(recruiterResource, User.class);
    }

    public RecruiterResource toResource(User model){
        return mapper.map(model, RecruiterResource.class);
    }
}
