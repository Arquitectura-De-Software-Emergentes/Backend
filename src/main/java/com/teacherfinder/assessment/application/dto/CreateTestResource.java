package com.teacherfinder.assessment.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTestResource {
    private Long id;
    private Long recruiterId;
    private String title;
    private Long minimunScore;
}
