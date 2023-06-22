package com.teacherfinder.assessment.api.rest;

import java.util.List;

import com.teacherfinder.assessment.application.dto.*;
import com.teacherfinder.assessment.application.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teacherfinder.assessment.domain.service.AssesmentService;

@CrossOrigin(origins = "*", methods = {
    RequestMethod.POST,
    RequestMethod.PUT,
    RequestMethod.DELETE,
    RequestMethod.GET
})
@RestController
@RequestMapping("/assessments")
public class AssesmentController {

    @Autowired
    AssesmentService service;

    @Autowired
    TestActivityMapper testMapper;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    AssessmentMapper assessmentMapper;

    @Autowired
    TestResultMapper testResultMapper;

    @Autowired
    VideoPresentationMapper videoPresentationMapper;

    @PostMapping
    public AssessmentResource createAssessment(@RequestBody CreateAssessmentResource resource){
        return assessmentMapper.toResource(service.createAssessment(assessmentMapper.toModel(resource)));
    }

    @GetMapping("job-offers/{jobOfferId}")
    public AssessmentResource getAssessment(@RequestParam("jobOfferId") Long jobOfferId){
        return assessmentMapper.toResource(service.getAssessmentByJobOfferId(jobOfferId));
    }

    @PostMapping("{assessmentId}/tests")
    public TestResource addTest(@RequestParam("assessmentId") Long assessmentId,@RequestBody CreateTestResource resource){
        return testMapper.toResource(service.addTest(assessmentId,testMapper.toModel(resource)));
    }

    @PostMapping("{assessmentId}/tests/questions")
    public ResponseEntity<String> AddQuestion(@RequestParam("assessmentId") Long assessmentId, @RequestBody CreateQuestionResource resource){
        return service.addQuestion(assessmentId, questionMapper.toModel(resource));
    }

    @GetMapping("{assessmentId}/tests")
    public TestResource GetTestById(@RequestParam("assessmentId") Long assessmentId){
        return testMapper.toResource(service.GetTestByAssessmentId(assessmentId));
    }
 
    @PostMapping("{assessmentId}/tests/applicant/{applicantId}/submit")
    public TestResultResource submitTest(@RequestParam("assessmentId") Long assessmentId,@RequestParam("applicantId") Long applicantId ,@RequestBody List<QuestionResource> resource){
        return testResultMapper.toResource(service.calculateScore(assessmentId, applicantId, questionMapper.ResourceListToModel(resource)));
    }
    
    @GetMapping("{assessmentId}/tests/applicant/{applicantId}")
    public TestResultResource GetResult(@RequestParam("assessmentId") Long assessmentId,@RequestParam("applicantId") Long applicantId){
        return testResultMapper.toResource(service.getResultByTestResultId(assessmentId, applicantId));
    }

    @PostMapping("/{assessmentId}/video-presentations")
    public VideoPresentationResource addVideoPresentation(@RequestParam("assessmentId") Long assessmentId ,@RequestBody CreateVideoPresentationResource resource){
        return videoPresentationMapper.toResource(service.createVideoPresentation(assessmentId , videoPresentationMapper.toModel(resource)));

    }

    @GetMapping("{assessmentId}/video-presentations{videoId}")
    public VideoPresentationResource GetVideoPresentationtById(@RequestParam("assessmentId") Long assessmentId,@RequestParam("videoId") Long videoId ){
        return videoPresentationMapper.toResource(service.getVideoPresentationById(assessmentId,videoId));
    }

}
