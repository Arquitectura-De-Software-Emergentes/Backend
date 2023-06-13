package com.teacherfinder.assessment.domain.model.entity;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestActivity implements Activity{
    private Long id;
    private Long minimunScore;
    private Date initialAvailableDate;
    private Date endAvailableDate;
    private List<Question> questions;
    private Long score;

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
    @Override
    public Boolean hasPassed() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hasPassed'");
    }
    @Override
    public Long getScore() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getScore'");
    }
}
