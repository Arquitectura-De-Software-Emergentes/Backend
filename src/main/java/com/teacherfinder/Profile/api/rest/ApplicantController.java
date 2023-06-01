package com.teacherfinder.Profile.api.rest;


import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.teacherfinder.Profile.dtos.ApplicantResource;
import com.teacherfinder.Profile.dtos.CreateApplicantResource;
import com.teacherfinder.Profile.mapper.ApplicantMapper;
import com.teacherfinder.Profile.domain.model.aggregate.Applicant;
import com.teacherfinder.Profile.domain.model.valueObjects.CurriculumVitae;
import com.teacherfinder.Profile.domain.service.ApplicantService;

@CrossOrigin(origins = "*", methods ={
    RequestMethod.POST,
    RequestMethod.GET,
    RequestMethod.PUT,
    RequestMethod.DELETE
})

@RestController
@RequestMapping("api/v1/applicant")
public class ApplicantController {
    
    @Autowired
    ApplicantService service;

    @Autowired
    ApplicantMapper mapper;

    @PostMapping
    public ApplicantResource create(@RequestBody CreateApplicantResource createApplicantResource){

        Applicant applicant = mapper.toModel(createApplicantResource);

        Applicant response = service.create(applicant);

        return mapper.toResource(response);    
    }

    @PutMapping("/{id}/cv")
    public CurriculumVitae uploadCV(@PathVariable("id") Long applicantId, @RequestParam("cv") CurriculumVitae cv) {
        
        return service.uploadCV(applicantId,cv);
    }

    @GetMapping("/{id}/cv")
    public CurriculumVitae getCV(@PathVariable("id") Long appliacntId) {
        return service.getCv(appliacntId);
    }
}
