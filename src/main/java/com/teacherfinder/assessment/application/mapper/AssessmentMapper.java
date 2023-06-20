package com.teacherfinder.assessment.application.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.teacherfinder.assessment.application.dto.AssessmentResource;
import com.teacherfinder.assessment.application.dto.CreateAssessmentResource;
import com.teacherfinder.assessment.domain.model.aggregate.Assessment;
import com.teacherfinder.shared.mapping.EnhancedModelMapper;

public class AssessmentMapper {
    @Autowired
    EnhancedModelMapper mapper;

    public Assessment toModel(CreateAssessmentResource resource){
        return mapper.map(resource, Assessment.class);
    }

    public AssessmentResource toResource(Assessment model){
        return mapper.map(model, AssessmentResource.class);
    }
}
