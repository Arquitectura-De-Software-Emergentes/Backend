package com.teacherfinder.assessment.domain.model.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InterviewActivity implements Activity{
    private Long id;
    private String callUrl;
    private Date interviewDate;
    private Double score;
    @Override
    public Boolean hasPassed() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hasPassed'");
    }
}
