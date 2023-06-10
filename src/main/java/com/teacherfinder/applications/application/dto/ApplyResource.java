package com.teacherfinder.applications.application.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplyResource {
    @NotNull(message = "applicant id is required")
    private Long applicantId;
    
    @NotNull(message = "job offer id is required")
    private Long jobOfferId;
}
