package com.teacherfinder.assessment.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestResource {
    private Long id;
    private String title;
    private Long numQuestions;
    private Long minimunScore;
}
