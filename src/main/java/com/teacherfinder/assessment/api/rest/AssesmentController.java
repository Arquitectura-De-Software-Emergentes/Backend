package com.teacherfinder.assessment.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.teacherfinder.assessment.application.dto.CreateTestResource;
import com.teacherfinder.assessment.application.mapper.TestActivityMapper;
import com.teacherfinder.assessment.domain.service.AssesmentService;

@CrossOrigin(origins = "*", methods = {
    RequestMethod.POST,
    RequestMethod.PUT,
    RequestMethod.DELETE,
    RequestMethod.GET
})
@RestController
@RequestMapping("/assessment")
public class AssesmentController {

    @Autowired
    AssesmentService service;

    @Autowired
    TestActivityMapper mapper;

    @PostMapping("/test")
    public ResponseEntity<String> createTest(@RequestBody CreateTestResource resource){
        return service.createTest(mapper.toModel(resource));
    }
    
}
