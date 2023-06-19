package com.teacherfinder.assessment.domain.service;

import org.springframework.http.ResponseEntity;

import com.teacherfinder.assessment.domain.model.entity.Question;
import com.teacherfinder.assessment.domain.model.entity.TestActivity;

public interface AssesmentService {
    TestActivity createTest(TestActivity test);
    ResponseEntity<String> addQuestion(Long testId, Question question);
    TestActivity GetTestById(Long testId);
}
