package com.teacherfinder.assessment.application.dto;

import java.time.LocalDate;

import com.teacherfinder.assessment.domain.model.valueObject.TestResultId;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestResultResource {
    private TestResultId testResultId;

    private LocalDate submitDate;

    private Long score;

    private Long testId;

    private Boolean hasPassed;
}
