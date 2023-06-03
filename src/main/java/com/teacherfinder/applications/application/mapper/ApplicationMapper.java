package com.teacherfinder.applications.application.mapper;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.teacherfinder.applications.application.dto.ApplicationResource;
import com.teacherfinder.applications.domain.model.aggregate.Application;
import com.teacherfinder.shared.mapping.EnhancedModelMapper;

public class ApplicationMapper implements Serializable{
    
    @Autowired
    EnhancedModelMapper mapper;

    public ApplicationResource toResource(Application model){
        return mapper.map(model, ApplicationResource.class);
    }
}
