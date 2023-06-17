package com.teacherfinder.assessment.application.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateQuestionResource {

    @NotBlank(message = "The statement is required")
    private String statement;

    private String option1;

    private String option2;

    private String option3;

    private String option4;

    private String reponse;
}
