package com.teacherfinder.assessment.domain.factory;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.teacherfinder.assessment.domain.model.entity.TestActivity;
import com.teacherfinder.assessment.domain.model.entity.TestResult;
import com.teacherfinder.assessment.domain.model.valueObject.TestResultId;

@Component
public class TestResultFactory {
    public TestResult createTestResult(Long assessmentId, Long applicantId, Long score, TestActivity test, Boolean hasPassed ){
        TestResultId testResultId = new TestResultId(applicantId,assessmentId);
        LocalDate submitDate = LocalDate.now();
        return new TestResult(testResultId,submitDate,score, hasPassed,test);
    }
}
