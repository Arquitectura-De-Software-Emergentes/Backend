package com.teacherfinder.assessment.application.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssessmentResource {
    private Long assessmentId;
    private Long jobOfferId;
    private Date initialAvailableDate;
    private Date endAvailableDate;
    private Long testId;
}
