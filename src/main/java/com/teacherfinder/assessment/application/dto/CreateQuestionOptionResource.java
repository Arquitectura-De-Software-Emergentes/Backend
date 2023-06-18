package com.teacherfinder.assessment.application.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateQuestionOptionResource {
    
    @NotBlank(message = "response is required")
    private String response;
    private Boolean isCorrect;
}
