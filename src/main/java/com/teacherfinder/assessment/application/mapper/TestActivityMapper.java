package com.teacherfinder.assessment.application.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.teacherfinder.assessment.application.dto.CreateTestResource;
import com.teacherfinder.assessment.application.dto.TestDetailResource;
import com.teacherfinder.assessment.application.dto.TestResource;
import com.teacherfinder.assessment.domain.model.entity.TestActivity;
import com.teacherfinder.shared.mapping.EnhancedModelMapper;

public class TestActivityMapper {
    
    @Autowired
    EnhancedModelMapper mapper;

    public TestActivity toModel(CreateTestResource resource){
        return mapper.map(resource, TestActivity.class);
    }

    public TestDetailResource toDetailResource(TestActivity model){
        return mapper.map(model, TestDetailResource.class);
    }

    public TestResource toResource(TestActivity model){
        return mapper.map(model, TestResource.class);
    }

    public List<TestResource> modelListToResource(List<TestActivity> modelList){
        return mapper.mapList(modelList, TestResource.class);
    }
}
