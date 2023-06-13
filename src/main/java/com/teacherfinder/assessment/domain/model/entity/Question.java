package com.teacherfinder.assessment.domain.model.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Question {
    private Long id;
    private String statement;
    private List<QuestionOption> options;
}
