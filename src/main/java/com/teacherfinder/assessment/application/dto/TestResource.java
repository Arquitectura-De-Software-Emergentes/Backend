package com.teacherfinder.assessment.application.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestResource {
    private Long id;
    private Long numQuestions;
    private Long minimunScore;
    private List<QuestionResource> questions;

}
