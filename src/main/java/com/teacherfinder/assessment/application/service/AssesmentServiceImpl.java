package com.teacherfinder.assessment.application.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.teacherfinder.assessment.domain.model.entity.*;
import com.teacherfinder.assessment.domain.model.entity.QuestionOption;
import com.teacherfinder.assessment.domain.repository.*;
import com.teacherfinder.assessment.domain.repository.QuestionOptionRepository;
import com.teacherfinder.assessment.domain.repository.QuestionRepository;
import com.teacherfinder.assessment.domain.repository.TestActivityRepository;
import com.teacherfinder.profile.domain.repository.ApplicantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.teacherfinder.assessment.domain.factory.TestResultFactory;
import com.teacherfinder.assessment.domain.model.aggregate.Assessment;
import com.teacherfinder.assessment.domain.model.valueObject.TestResultId;
import com.teacherfinder.assessment.domain.service.AssesmentService;
import com.teacherfinder.shared.exception.ResourceNotFoundException;
import com.teacherfinder.shared.exception.ResourceValidationException;

@Service
public class AssesmentServiceImpl implements AssesmentService {

    private static final String TEST = "test";
    private static final String TEST_RESULT = "test's result";
    private static final String QUESTION = "question";
    private static final String OPTION = "option";
    private static final String ASSESSMENT = "assessment";
    private static final String VIDEO_PRESENTATION = "Video Presentation";
    private final TestActivityRepository testRepository;
    private final QuestionRepository questionRepository;
    private final QuestionOptionRepository optionRepository;
    private final TestResultRepository testResultRepository;
    private final TestResultFactory testResultFactory;
    private final AssesmentRepository assessmentRepository;
    private final Validator validator;
    private final ApplicantRepository applicantRepository;
    private final VideoPresentationRepository videoPresentationRepository;


    public AssesmentServiceImpl(TestActivityRepository testRepository, QuestionRepository questionRepository,
            QuestionOptionRepository optionRepository, TestResultRepository testResultRepository,
            TestResultFactory testResultFactory, AssesmentRepository assesmentRepository, Validator validator,
                                ApplicantRepository applicantRepository,
                                VideoPresentationRepository videoPresentationRepository) {
        this.testRepository = testRepository;
        this.questionRepository = questionRepository;
        this.optionRepository = optionRepository;
        this.testResultRepository = testResultRepository;
        this.testResultFactory = testResultFactory;
        this.assessmentRepository = assesmentRepository;
        this.validator = validator;
        this.applicantRepository = applicantRepository;
        this.videoPresentationRepository = videoPresentationRepository;
    }

    @Override
    public Assessment createAssessment(Assessment assessment) {
        Set<ConstraintViolation<Assessment>> violations = validator.validate(assessment);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ASSESSMENT, violations);

        return assessmentRepository.save(assessment);
    }

    @Override
    public Assessment getAssessmentByJobOfferId(Long jobOfferId) {
        return assessmentRepository.findByJobOfferId(jobOfferId)
            .orElseThrow(()->new ResourceNotFoundException(ASSESSMENT, jobOfferId));
    }

    @Override
    public TestActivity addTest(Long assessmentId,TestActivity test) {

        Assessment assessment = assessmentRepository.findById(assessmentId)
            .orElseThrow(()->new ResourceNotFoundException(ASSESSMENT, assessmentId));
        
        if(assessment.getTest() != null) 
            throw new ResourceValidationException("You have already resgistered a Test");

        Set<ConstraintViolation<TestActivity>> violations = validator.validate(test);
        if (!violations.isEmpty())
            throw new ResourceValidationException(TEST, violations);

        return testRepository.save(test.withAssessment(assessment));

    }

    @Override
    public TestActivity GetTestByAssessmentId(Long assessmentId) {
        return testRepository.findByAssessmentAssessmentId(assessmentId)
                .orElseThrow(() -> new ResourceNotFoundException(TEST, assessmentId));
    }

    @Override
    @Transactional
    public ResponseEntity<String> addQuestion(Long assessmentId, Question question) {

        TestActivity currentTest = validateQuestion(assessmentId, question);

        Question result = questionRepository.save(question.withTest(currentTest));

        addOptions(question.getOptions(), result);

        currentTest.addQuestion(question);

        testRepository.save(currentTest);

        return new ResponseEntity<String>("Question was successfully saved", HttpStatus.OK);
    }

    @Override
    @Transactional
    public TestResult calculateScore(Long assessmentId, Long applicantId ,List<Question> questions) {

        TestActivity currentTest = testRepository.findByAssessmentAssessmentId(assessmentId)
                .orElseThrow(() -> new ResourceNotFoundException(TEST, assessmentId));

        Long score = 0L;
        Boolean hasPassed = false;

        for (Question question : questions) {
            if(VerifyAnswer(question.getReponseId())) score += question.getPoints();
        }

        if(score>= currentTest.getMinimunScore()) hasPassed = true;

        TestResult result = testResultFactory.createTestResult(assessmentId ,applicantId,score,currentTest, hasPassed);     
 
        return testResultRepository.save(result);
    }

    private Boolean VerifyAnswer(Long optionId) {

        if(optionId == 0L) return false;

        QuestionOption selectedOption = optionRepository.findById(optionId)
                .orElseThrow(() -> new ResourceNotFoundException(OPTION, optionId));
        
        return selectedOption.getIsCorrect();
    }

    private TestActivity validateQuestion(Long assessmentId, Question question) {
        TestActivity currentTest = testRepository.findByAssessmentAssessmentId(assessmentId)
                .orElseThrow(() -> new ResourceNotFoundException(TEST, assessmentId));

        Set<ConstraintViolation<Question>> violations = validator.validate(question);

        if (!violations.isEmpty())
            throw new ResourceValidationException(QUESTION, violations);

        return currentTest;
    }

    private void addOptions(List<QuestionOption> options, Question question) {

        for (QuestionOption option : options) {
            Set<ConstraintViolation<QuestionOption>> violations = validator.validate(option);

            if (!violations.isEmpty())
                throw new ResourceValidationException(OPTION, violations);
            optionRepository.save(option.withQuestion(question));
        }
    }

    @Override
    public TestResult getResultByTestResultId(Long assessmentId, Long applicantId) {
        TestResultId testResultId = new TestResultId(applicantId, assessmentId);
        return testResultRepository.findByTestResultId(testResultId)
            .orElseThrow(()-> new ResourceNotFoundException(TEST_RESULT));
    }

    @Override
    public VideoPresentation createVideoPresentation(Long assessmentId,VideoPresentation videoPresentation) {
        Set<ConstraintViolation<VideoPresentation>>violations = validator.validate(videoPresentation);
        if(!violations.isEmpty()) {
            throw new ResourceValidationException(VIDEO_PRESENTATION, violations);
        }
        if(videoPresentation.getDuration()> (double)3.00) {
            throw  new ResourceValidationException("Video should be as a max 3 minutes ");
        }
        Assessment assessment = assessmentRepository.findById(assessmentId).orElseThrow(() -> new ResourceNotFoundException(ASSESSMENT, assessmentId));
        videoPresentation.setAssessment(assessment);
        return videoPresentationRepository.save(videoPresentation);
    }

    @Override
    public List<VideoPresentation> getAllVideoPresentationsByApplicantId() {
        return null;
    }

    @Override
    public VideoPresentation getVideoPresentationById(Long assessmentId,Long iD) {
        if(!assessmentRepository.existsById(assessmentId)){
            throw new ResourceNotFoundException(ASSESSMENT, assessmentId);
        }
        return videoPresentationRepository.findById(iD).orElseThrow(()-> new ResourceNotFoundException(VIDEO_PRESENTATION , iD));
    }
}
