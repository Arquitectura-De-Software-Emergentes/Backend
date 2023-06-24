package com.teacherfinder.profile.api.rest;

import com.teacherfinder.profile.application.dto.ApplicantProfileResource;
import com.teacherfinder.profile.application.dto.CreateApplicantResource;
import com.teacherfinder.profile.application.dto.CreateJobExperienceInformationResource;
import com.teacherfinder.profile.application.dto.JobExperienceInformationResource;
import com.teacherfinder.profile.application.dto.UpdateApplicantProfileResource;
import com.teacherfinder.profile.application.mapper.ApplicantProfileMapper;
import com.teacherfinder.profile.application.mapper.JobExperienceInformationMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.teacherfinder.profile.application.dto.ApplicantResource;
import com.teacherfinder.profile.application.mapper.ApplicantMapper;
import com.teacherfinder.profile.domain.model.aggregate.Applicant;
import com.teacherfinder.profile.domain.model.valueObjects.CurriculumVitae;
import com.teacherfinder.profile.domain.service.ApplicantService;

@CrossOrigin(origins = "*", methods ={
    RequestMethod.POST,
    RequestMethod.GET,
    RequestMethod.PUT,
    RequestMethod.DELETE
})

@RestController
@RequestMapping("api/v1/applicants")
public class ApplicantController {
    
    @Autowired
    ApplicantService service;

    @Autowired
    ApplicantMapper applicantMapper;

    @Autowired
    ApplicantProfileMapper profileMapper;

    @Autowired
    JobExperienceInformationMapper jobExperienceMapper;

    @GetMapping("/{id}/cv")
    public CurriculumVitae getCV(@PathVariable("id") Long appliacntId) {
        return service.getCv(appliacntId);
    }
    @PostMapping
    public ApplicantResource register(@RequestBody CreateApplicantResource createApplicantResource){

        Applicant applicant = applicantMapper.toModel(createApplicantResource);

        Applicant response = service.register(applicant);

        return applicantMapper.toResource(response);
    }

    @PostMapping("/experience")
    public JobExperienceInformationResource addJobExperience(@RequestBody CreateJobExperienceInformationResource jobExperienceInformationResource){

        return jobExperienceMapper.toResource(service.addJobExperience(jobExperienceMapper.toModel(jobExperienceInformationResource)));

    }

    @PutMapping("/{id}/cv")
    public CurriculumVitae uploadCV(@PathVariable("id") Long applicantId, @RequestBody CurriculumVitae cv) {
        
        return service.uploadCV(applicantId,cv);
    }

    @PutMapping("/{id}/profile")
    public ApplicantProfileResource updateProfile(@PathVariable("id") Long applicantId, @RequestBody UpdateApplicantProfileResource updateApplicantProfileResource){
         return profileMapper.toResource(service.updateProfile(applicantId,profileMapper.toModel(updateApplicantProfileResource)));
    }

    @GetMapping("/{id}/profile")
    public ApplicantProfileResource getProfile(@PathVariable("id") Long appliantId){
        return profileMapper.toResource(service.getApplicantProfile(appliantId));
    }


}
