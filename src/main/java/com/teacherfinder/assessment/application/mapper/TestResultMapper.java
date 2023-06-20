package com.teacherfinder.assessment.application.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.teacherfinder.assessment.application.dto.TestResultResource;
import com.teacherfinder.assessment.domain.model.entity.TestResult;
import com.teacherfinder.shared.mapping.EnhancedModelMapper;

public class TestResultMapper {
    @Autowired
    EnhancedModelMapper mapper;

    public TestResultResource toResource(TestResult model){
        return mapper.map(model, TestResultResource.class);
    }
}
