package com.teacherfinder.assessment.application.service;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.teacherfinder.assessment.domain.model.entity.Question;
import com.teacherfinder.assessment.domain.model.entity.TestActivity;
import com.teacherfinder.assessment.domain.repository.QuestionRepository;
import com.teacherfinder.assessment.domain.repository.TestActivityRepository;
import com.teacherfinder.assessment.domain.service.AssesmentService;
import com.teacherfinder.shared.exception.ResourceNotFoundException;
import com.teacherfinder.shared.exception.ResourceValidationException;

@Service
public class AssesmentServiceImpl implements AssesmentService{

    private static final String TEST = "test";
    private static final String QUESTION = "question";
    private final TestActivityRepository testRepository;
    private final QuestionRepository questionRepository;
    private final Validator validator;

    public AssesmentServiceImpl(TestActivityRepository testRepository, QuestionRepository questionRepository,
            Validator validator) {
        this.testRepository = testRepository;
        this.questionRepository = questionRepository;
        this.validator = validator;
    }

    @Override
    public ResponseEntity<String> createTest(TestActivity test) {
        Set<ConstraintViolation<TestActivity>> violations = validator.validate(test);
        if(!violations.isEmpty())
            throw new ResourceValidationException(TEST, violations);
            
        testRepository.save(test);
        
        return new ResponseEntity<String>("Test was successfully saved", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> addQuestion(Long testId,Question question) {
        Set<ConstraintViolation<Question>> violations = validator.validate(question);

        if(!violations.isEmpty())
            throw new ResourceValidationException(QUESTION, violations);

        TestActivity test = testRepository.findById(testId)
            .orElseThrow(()-> new ResourceNotFoundException(QUESTION, testId));

        questionRepository.save(question.withTest(test));

        return new ResponseEntity<String>("Question was successfully saved", HttpStatus.OK);
    }
    
}
