package com.teacherfinder.assessment.application.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTestResource {
    
    private Long minimunScore;

    private Date initialAvailableDate;
    private Date endAvailableDate;

    private Boolean enable = false;

}
