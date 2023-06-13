package com.teacherfinder.assessment.domain.model.entity;


public interface Activity {
    void execute();
    Boolean hasPassed();
    Long getScore();
}
