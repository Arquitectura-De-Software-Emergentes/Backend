package com.teacherfinder.assessment.application.dto;

import java.util.Date;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestResource {
    private Long id;
    private Date initialAvailableDate;
    private Date endAvailableDate;
    private Boolean enable;
    private Long numQuestions;
    private Set<QuestionResource> questions;

}
