package com.teacherfinder.applications.api.rest;

import java.util.List;

import com.teacherfinder.applications.application.dto.GetApplicationByApplicantIdResource;
import com.teacherfinder.applications.domain.model.entity.JobOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.teacherfinder.applications.application.dto.GetApplicationByOfferIdResource;
import com.teacherfinder.applications.application.mapper.ApplicationMapper;
import com.teacherfinder.applications.domain.service.ApplicationService;

@CrossOrigin(origins = "*", methods = {
    RequestMethod.POST,
    RequestMethod.GET,
    RequestMethod.PUT
})
@RestController
@RequestMapping("api/v1/applications")
public class ApplicationController {
    
    @Autowired
    ApplicationService service;

    @Autowired
    ApplicationMapper mapper;

    @GetMapping("job-offers/{jobOfferId}")
    public List<GetApplicationByOfferIdResource> getApplicationsByOfferId(@PathVariable("jobOfferId") Long jobOfferId){
        return mapper.modelListToResourceByOfferId(service.getApplicationsByOfferId(jobOfferId));
    }

    @GetMapping("applicants/{applicantId}")
    public List<JobOffer> getApplicationsByApplicantId(@PathVariable("applicantId") Long applicantId){
        return service.getApplicationsByApplicantId(applicantId);
    }
}
