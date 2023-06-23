package com.teacherfinder.assessment.domain.factory;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.teacherfinder.assessment.domain.model.aggregate.Assessment;

@Component
public class AssessmentFactory {
    
    public Assessment generateAssessment(Long jobOfferId, Date initialAvailableDate, Date endAvailableDate){

        return new Assessment(jobOfferId, jobOfferId, initialAvailableDate, endAvailableDate, null, null);
    }
}
