package com.teacherfinder.assessment.application.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateQuestionOptionResource {

    @NotBlank
    private String answer;

    private Boolean isCorrect;
}
