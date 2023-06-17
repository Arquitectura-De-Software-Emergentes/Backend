package com.teacherfinder.assessment.application.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.teacherfinder.assessment.application.dto.CreateQuestion;
import com.teacherfinder.assessment.application.dto.CreateQuestionResource;
import com.teacherfinder.assessment.domain.model.entity.Question;
import com.teacherfinder.shared.mapping.EnhancedModelMapper;

public class QuestionMapper {
    @Autowired
    EnhancedModelMapper mapper;

    public Question toModel(CreateQuestionResource resource) {
        return mapper.map( resource, Question.class );
    }

}
