package com.teacherfinder.assessment.application.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.teacherfinder.assessment.domain.model.entity.Question;
import com.teacherfinder.assessment.domain.model.entity.QuestionOption;
import com.teacherfinder.assessment.domain.model.entity.TestActivity;
import com.teacherfinder.assessment.domain.repository.QuestionOptionRepository;
import com.teacherfinder.assessment.domain.repository.QuestionRepository;
import com.teacherfinder.assessment.domain.repository.TestActivityRepository;
import com.teacherfinder.assessment.domain.service.AssesmentService;
import com.teacherfinder.shared.exception.ResourceNotFoundException;
import com.teacherfinder.shared.exception.ResourceValidationException;

@Service
public class AssesmentServiceImpl implements AssesmentService {

    private static final String TEST = "test";
    private static final String QUESTION = "question";
    private static final String OPTION = "option";
    private final TestActivityRepository testRepository;
    private final QuestionRepository questionRepository;
    private final QuestionOptionRepository optionRepository;
    private final Validator validator;

    public AssesmentServiceImpl(TestActivityRepository testRepository, QuestionRepository questionRepository,
            QuestionOptionRepository optionRepository, Validator validator) {
        this.testRepository = testRepository;
        this.questionRepository = questionRepository;
        this.optionRepository = optionRepository;
        this.validator = validator;
    }

    @Override
    public TestActivity GetTestById(Long testId) {
        return testRepository.findById(testId)
            .orElseThrow(()-> new ResourceNotFoundException(TEST, testId));
    }

    @Override
    public ResponseEntity<String> createTest(TestActivity test) {
        Set<ConstraintViolation<TestActivity>> violations = validator.validate(test);
        if (!violations.isEmpty())
            throw new ResourceValidationException(TEST, violations);

        testRepository.save(test);

        return new ResponseEntity<String>("Test was successfully saved", HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<String> addQuestion(Long testId, Question question) {
        Set<ConstraintViolation<Question>> violations = validator.validate(question);

        if (!violations.isEmpty())
            throw new ResourceValidationException(QUESTION, violations);

        TestActivity test = testRepository.findById(testId)
                .orElseThrow(() -> new ResourceNotFoundException(QUESTION, testId));

        Question result = questionRepository.save(question.withTest(test));

        addOptions(question.getOptions(), result);

        return new ResponseEntity<String>("Question was successfully saved", HttpStatus.OK);
    }

    private void addOptions(List<QuestionOption> options, Question question) {
        
        for (QuestionOption option : options) {
            Set<ConstraintViolation<QuestionOption>> violations = validator.validate(option);

            if (!violations.isEmpty())
                throw new ResourceValidationException(OPTION, violations);

            optionRepository.save(option.withQuestion(question));
        }
    }

}
