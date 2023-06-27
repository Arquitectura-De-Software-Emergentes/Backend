package com.teacherfinder.assessment.domain.service;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.teacherfinder.assessment.domain.model.entity.VideoPresentation;
import com.teacherfinder.assessment.domain.model.aggregate.Assessment;
import com.teacherfinder.assessment.domain.model.entity.Question;
import com.teacherfinder.assessment.domain.model.entity.TestActivity;
import com.teacherfinder.assessment.domain.model.entity.TestResult;

public interface AssesmentService {
    Assessment createAssessment(Long jobOfferId, Date initialAvailableDate, Date endAvailableDate);
    TestActivity GetTestByAssessmentId(Long assessmentId);
    TestResult calculateScore(Long assessmentId, Long applicantId,List<Question> questions);
    TestResult getResultByTestResultId(Long assessmentId, Long applicant);
    ResponseEntity<String> addTest(Long assessmentId, Long testId);

    TestActivity createTest(TestActivity test);
    ResponseEntity<String> addQuestion(Long testId, Question question);
    TestActivity getTestActivity(Long testId);
    List<TestActivity> getTestsActivityByRecruiterId(Long recruiterId);


    VideoPresentation createVideoPresentation (Long assessmentId ,VideoPresentation  videoPresentation);
    List<VideoPresentation> getAllVideoPresentationsByApplicantId();
    VideoPresentation getVideoPresentationById(Long assessmentId, Long Id);


}
