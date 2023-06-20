package com.teacherfinder.assessment.application.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAssessmentResource {
    private Long jobOfferId;
    private Date initialAvailableDate;
    private Date endAvailableDate;
    private Boolean enable = false;
}
