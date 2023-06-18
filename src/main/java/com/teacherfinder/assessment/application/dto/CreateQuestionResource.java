package com.teacherfinder.assessment.application.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateQuestionResource {

    @NotBlank(message = "The statement is required")
    private String statement;

    List<CreateQuestionOptionResource> options;

}
