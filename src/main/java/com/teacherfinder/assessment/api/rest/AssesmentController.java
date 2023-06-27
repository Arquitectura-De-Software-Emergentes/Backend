package com.teacherfinder.assessment.api.rest;

import java.util.List;

import com.teacherfinder.assessment.application.dto.*;
import com.teacherfinder.assessment.application.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
@RequestMapping("api/v1/assessments")
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

    @PostMapping("/tests")
    public TestDetailResource CreateTest(@RequestBody CreateTestResource resource){
        return testMapper.toDetailResource(service.createTest(testMapper.toModel(resource)));
    }

    @PostMapping("/tests/{testId}/questions")
    public ResponseEntity<String> AddQuestion(@RequestParam("testId") Long testId, @RequestBody CreateQuestionResource resource){
        return service.addQuestion(testId, questionMapper.toModel(resource));
    }

    @GetMapping("{jobOfferId}/tests")
    public TestDetailResource GetTestById(@RequestParam("jobOfferId") Long assessmentId){
        return testMapper.toDetailResource(service.GetTestByAssessmentId(assessmentId));
    }
 
    @PostMapping("{jobOfferId}/tests/applicant/{applicantId}/submit")
    public TestResultResource submitTest(@RequestParam("jobOfferId") Long assessmentId,@RequestParam("applicantId") Long applicantId ,@RequestBody List<QuestionResource> resource){
        return testResultMapper.toResource(service.calculateScore(assessmentId, applicantId, questionMapper.ResourceListToModel(resource)));
    }
    
    @GetMapping("{jobOfferId}/test-results/applicant/{applicantId}")
    public TestResultResource GetResult(@RequestParam("jobOfferId") Long assessmentId,@RequestParam("applicantId") Long applicantId){
        return testResultMapper.toResource(service.getResultByTestResultId(assessmentId, applicantId));
    }

    @PostMapping("/{jobOfferId}/video-presentations")
    public VideoPresentationResource addVideoPresentation(@RequestParam("jobOfferId") Long assessmentId ,@RequestBody CreateVideoPresentationResource resource){
        return videoPresentationMapper.toResource(service.createVideoPresentation(assessmentId , videoPresentationMapper.toModel(resource)));

    }

    @GetMapping("{jobOfferId}/video-presentations{videoId}")
    public VideoPresentationResource GetVideoPresentationtById(@RequestParam("jobOfferId") Long assessmentId,@RequestParam("videoId") Long videoId ){
        return videoPresentationMapper.toResource(service.getVideoPresentationById(assessmentId,videoId));
    }

    @GetMapping("tests/{testId}")
    public TestDetailResource getTestById(@RequestParam("testId") Long testId){
        return testMapper.toDetailResource(service.getTestActivity(testId));
    }

    @GetMapping("recuiter/{recuiterId}/tests")
    public List<TestResource> getTestByRecruiterId(@RequestParam("recuiterId") Long recruiterId){
        return testMapper.modelListToResource(service.getTestsActivityByRecruiterId(recruiterId));
    }

    @PutMapping("{jobOfferId}/test/{testId}")
    public ResponseEntity<String> toAssign(@RequestParam("assessmentId") Long assessmentId, @RequestParam("testId") Long testId ){
        return service.addTest(assessmentId, testId);
    }

}
