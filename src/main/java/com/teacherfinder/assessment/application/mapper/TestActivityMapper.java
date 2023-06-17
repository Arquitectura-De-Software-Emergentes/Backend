package com.teacherfinder.assessment.application.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.teacherfinder.assessment.application.dto.CreateTestResource;
import com.teacherfinder.assessment.domain.model.entity.TestActivity;
import com.teacherfinder.shared.mapping.EnhancedModelMapper;

public class TestActivityMapper {
    
    @Autowired
    EnhancedModelMapper mapper;

    public TestActivity toModel(CreateTestResource resource){
        return mapper.map(resource, TestActivity.class);
    }
}
