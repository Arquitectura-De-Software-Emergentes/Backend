package com.teacherfinder.assessment.application.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionResource {
    private Long id;
    private String statement;
    private List<QuestionOptionResource> options;
    private Long responseId;
    private Long points;
}
