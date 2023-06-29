package com.teacherfinder.assessment.application.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.teacherfinder.assessment.application.dto.CreateQuestionResource;
import com.teacherfinder.assessment.application.dto.QuestionResource;
import com.teacherfinder.assessment.domain.model.entity.Question;
import com.teacherfinder.shared.mapping.EnhancedModelMapper;

public class QuestionMapper {
    @Autowired
    EnhancedModelMapper mapper;

    public Question toModel(CreateQuestionResource resource) {
        return mapper.map( resource, Question.class );
    }

    public QuestionResource toResource(Question model){
        return mapper.map(model, QuestionResource.class );
    }

    public List<Question> ResourceListToModel(List<QuestionResource> resources){
        return mapper.mapList(resources, Question.class );
    }

}
