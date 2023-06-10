package com.teacherfinder.applications.application.mapper;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.teacherfinder.applications.application.dto.ApplicationResource;
import com.teacherfinder.applications.domain.model.aggregate.Application;
import com.teacherfinder.shared.mapping.EnhancedModelMapper;

public class ApplicationMapper implements Serializable{
    
    @Autowired
    EnhancedModelMapper mapper;

    public List<ApplicationResource> modelListToResource(List<Application> model){
        return mapper.mapList(model, ApplicationResource.class);
    }
}
