package com.teacherfinder.assessment.domain.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.teacherfinder.assessment.domain.model.aggregate.Assessment;
import com.teacherfinder.assessment.domain.model.entity.Question;
import com.teacherfinder.assessment.domain.model.entity.TestActivity;
import com.teacherfinder.assessment.domain.model.entity.TestResult;

public interface AssesmentService {
    Assessment createAssessment(Assessment assessment);
    Assessment getAssessmentByJobOfferId(Long jobOfferId);
    TestActivity addTest(Long assessmentId,TestActivity test);
    ResponseEntity<String> addQuestion(Long assessmentId, Question question);
    TestActivity GetTestByAssessmentId(Long assessmentId);
    TestResult calculateScore(Long assessmentId, Long applicantId,List<Question> questions);
    TestResult getResultByTestResultId(Long assessmentId, Long applicant);
}
