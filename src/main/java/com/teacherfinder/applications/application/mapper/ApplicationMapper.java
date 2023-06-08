package com.teacherfinder.applications.application.mapper;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.teacherfinder.applications.application.dto.ApplicationResource;
import com.teacherfinder.applications.application.dto.ApplyResource;
import com.teacherfinder.applications.domain.model.aggregate.Application;
import com.teacherfinder.applications.domain.model.valueObjects.ApplicationId;
import com.teacherfinder.shared.mapping.EnhancedModelMapper;

public class ApplicationMapper implements Serializable{
    
    @Autowired
    EnhancedModelMapper mapper;

    public ApplicationResource toResource(Application model){
        return mapper.map(model, ApplicationResource.class);
    }

    public ApplicationId toApplicationId(ApplyResource resource){
        return mapper.map(resource, ApplicationId.class);
    }
}
