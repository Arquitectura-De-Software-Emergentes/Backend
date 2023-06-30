package com.teacherfinder.assessment.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoPresentationResource {
    Long id;
    Long applicantId;
    String feedback;
    Long assessmentId;
}
