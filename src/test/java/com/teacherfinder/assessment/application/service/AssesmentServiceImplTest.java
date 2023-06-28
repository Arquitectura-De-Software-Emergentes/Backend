package com.teacherfinder.assessment.application.service;

import com.teacherfinder.assessment.domain.factory.AssessmentFactory;
import com.teacherfinder.assessment.domain.factory.TestResultFactory;
import com.teacherfinder.assessment.domain.model.aggregate.Assessment;
import com.teacherfinder.assessment.domain.model.entity.*;
import com.teacherfinder.assessment.domain.model.valueObject.TestResultId;
import com.teacherfinder.assessment.domain.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.util.*;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class) class AssesmentServiceImplTest {

    @Mock
    private TestActivityRepository testRepository;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private QuestionOptionRepository optionRepository;

    @Mock
    private TestResultRepository testResultRepository;

    @Mock
    private TestResultFactory testResultFactory;

    @Mock
    private AssesmentRepository assessmentRepository;

    @Mock
    private Validator validator;

    @Mock
    private VideoPresentationRepository videoPresentationRepository;

    @Mock
    private AssessmentFactory assessmentFactory;

    @InjectMocks
    private AssesmentServiceImpl assesmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        // Configurar el comportamiento del mock testResultRepository
        Long assessmentId = 1L;
        Long applicantId = 1L;
        TestResultId testResultId = new TestResultId(applicantId, assessmentId);
        TestResult testResult = new TestResult();

        when(testResultRepository.findByTestResultId(testResultId)).thenReturn(Optional.of(testResult));
    }

    @Test
    public void testCreateAssessment() {
        // Mocking inputs
        Long jobOfferId = 1L;
        Date initialAvailableDate = new Date();
        Date endAvailableDate = new Date();

        Assessment assessment = new Assessment();

        when(assessmentFactory.generateAssessment(jobOfferId, initialAvailableDate, endAvailableDate))
                .thenReturn(assessment);
        when(validator.validate(assessment)).thenReturn(Collections.emptySet());
        when(assessmentRepository.save(assessment)).thenReturn(assessment);

        // Call the method
        Assessment result = assesmentService.createAssessment(jobOfferId, initialAvailableDate, endAvailableDate);

        // Verify the interactions and assertions
        verify(assessmentFactory).generateAssessment(jobOfferId, initialAvailableDate, endAvailableDate);
        verify(validator).validate(assessment);
        verify(assessmentRepository).save(assessment);
        assertEquals(assessment, result);
    }

    @Test
    public void testCreateTest() {
        // Mocking inputs
        TestActivity test = new TestActivity();

        when(validator.validate(test)).thenReturn(Collections.emptySet());
        when(testRepository.save(test)).thenReturn(test);

        // Call the method
        TestActivity result = assesmentService.createTest(test);

        // Verify the interactions and assertions
        verify(validator).validate(test);
        verify(testRepository).save(test);
        assertEquals(test, result);
    }

    @Test
    public void testGetTestByAssessmentId() {
        // Mocking inputs
        Long assessmentId = 1L;
        TestActivity testActivity = new TestActivity();

        when(testRepository.findByAssessmentAssessmentId(assessmentId)).thenReturn(Optional.of(testActivity));

        // Call the method
        TestActivity result = assesmentService.GetTestByAssessmentId(assessmentId);

        // Verify the interactions and assertions
        verify(testRepository).findByAssessmentAssessmentId(assessmentId);
        assertEquals(testActivity, result);
    }
    @Test
    public void testAddQuestion() {

        Long testId = 1L;
        Question question = new Question();
        question.setOptions(new ArrayList<>());
        TestActivity testActivity = new TestActivity();
        ResponseEntity<String> expectedResponse = new ResponseEntity<>("Question added successfully", HttpStatus.OK);

        Mockito.when(testRepository.findById(testId)).thenReturn(java.util.Optional.of(testActivity));
        Mockito.when(questionRepository.save(question)).thenReturn(question);

        ResponseEntity<String> actualResponse = assesmentService.addQuestion(testId, question);

        Assertions.assertEquals(expectedResponse, actualResponse);
        Mockito.verify(testRepository).save(testActivity);
    }

    @Test
    public void testCalculateScore() {
        Long assessmentId = 1L;
        Long applicantId = 2L;
        List<Question> questions = new ArrayList<>();

        TestActivity testActivity = new TestActivity();
        testActivity.setMinimunScore(80L);

        Question question1 = new Question();
        question1.setPoints(50L);
        question1.setResponseId(1L);

        Question question2 = new Question();
        question2.setPoints(30l);
        question2.setResponseId(2L);

        Question question3 = new Question();
        question3.setPoints(40L);
        question3.setResponseId(3L);

        questions.add(question1);
        questions.add(question2);
        questions.add(question3);

        Mockito.when(testRepository.findByAssessmentAssessmentId(assessmentId)).thenReturn(java.util.Optional.of(testActivity));
        QuestionOption option = new QuestionOption();
        option.setIsCorrect(true);
        Mockito.when(optionRepository.findById(anyLong())).thenReturn(java.util.Optional.of(option));

        TestResult expectedResult = new TestResult();
        Mockito.when(testResultRepository.save(Mockito.any(TestResult.class))).thenReturn(expectedResult);
        TestResult actualResult = assesmentService.calculateScore(assessmentId, applicantId, questions);
    }


    @Test
    void getAllVideoPresentationsByApplicantId() {
    }

    @Test
    void testGetVideoPresentationById() {
        Long assessmentId = 1L;
        Long videoPresentationId = 1L;
        Assessment assessment = new Assessment();
        VideoPresentation expectedVideoPresentation = new VideoPresentation();

        when(assessmentRepository.existsById(assessmentId)).thenReturn(true);
        when(videoPresentationRepository.findById(videoPresentationId)).thenReturn(Optional.of(expectedVideoPresentation));

        VideoPresentation result = assesmentService.getVideoPresentationById(assessmentId, videoPresentationId);

        assertEquals(expectedVideoPresentation, result);
    }

    @Test
    void testGetTestActivity() {
        Long testId = 1L;
        TestActivity expectedTestActivity = new TestActivity();

        when(testRepository.findById(testId)).thenReturn(Optional.of(expectedTestActivity));

        TestActivity result = assesmentService.getTestActivity(testId);

        assertEquals(expectedTestActivity, result);
    }


    @Test
    void getTestsActivityByRecruiterId() {
        Long recruiterId = 1L;
        List<TestActivity> expectedTests = new ArrayList<>();

        when(testRepository.findByRecruiterId(recruiterId)).thenReturn(expectedTests);

        List<TestActivity> result = assesmentService.getTestsActivityByRecruiterId(recruiterId);

        assertEquals(expectedTests, result);
    }

    @Test
    void addTest() {
        Long assessmentId = 1L;
        Long testId = 1L;
        TestActivity currentTest = new TestActivity();
        Assessment currentAssessment = new Assessment();

        when(testRepository.findById(testId)).thenReturn(Optional.of(currentTest));
        when(assessmentRepository.findById(assessmentId)).thenReturn(Optional.of(currentAssessment));
        when(assessmentRepository.save(currentAssessment)).thenReturn(currentAssessment);

        ResponseEntity<String> response = assesmentService.addTest(assessmentId, testId);

        assertEquals("Test assigned successfully", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    void getResultByTestResultId() {
        Long assessmentId = 1L;
        Long applicantId = 1L;
        TestResultId testResultId = new TestResultId(applicantId, assessmentId);
        TestResult testResult = new TestResult();

        when(testResultRepository.findByTestResultId(testResultId)).thenReturn(Optional.of(testResult));

        TestResult result = assesmentService.getResultByTestResultId(assessmentId, applicantId);

        assertEquals(testResult, result);
    }


    @Test
    void createVideoPresentation() {
        Long assessmentId = 1L;
        VideoPresentation videoPresentation = new VideoPresentation();
        Set<ConstraintViolation<VideoPresentation>> violations = new HashSet<>();

        when(validator.validate(videoPresentation)).thenReturn(violations);
        when(assessmentRepository.findById(assessmentId)).thenReturn(Optional.of(new Assessment()));
        when(videoPresentationRepository.save(videoPresentation)).thenReturn(videoPresentation);


    }


    // Add more test methods for the remaining methods in AssesmentServiceImpl

}
