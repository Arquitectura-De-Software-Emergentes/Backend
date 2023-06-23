package com.teacherfinder.assessment.api.internal;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.teacherfinder.assessment.application.mapper.AssessmentMapper;
import com.teacherfinder.assessment.domain.service.AssesmentService;

@Component
public class AssessmentFacade {
    
    @Autowired
    AssesmentService service;

    @Autowired
    AssessmentMapper assessmentMapper;

    public void createGenerateAssement(Long jobOfferId, Date initialAvailableDate, Date endAvailableDate){
        service.createAssessment(jobOfferId, initialAvailableDate, endAvailableDate);
    }

}
