package com.teacherfinder.assessment.application.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.teacherfinder.assessment.application.dto.CreateQuestionOptionResource;
import com.teacherfinder.assessment.domain.model.entity.QuestionOption;
import com.teacherfinder.shared.mapping.EnhancedModelMapper;

public class QuestionOptionMapper {
    @Autowired
    EnhancedModelMapper mapper;

    public QuestionOption toModel(CreateQuestionOptionResource resource){
        return mapper.map(resource, QuestionOption.class);
    }
}
