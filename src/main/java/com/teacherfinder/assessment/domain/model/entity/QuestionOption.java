package com.teacherfinder.assessment.domain.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionOption {
    private Long id;
    private String answer;
    private Boolean isCorrect;
}
