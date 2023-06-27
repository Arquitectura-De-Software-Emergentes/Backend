package com.teacherfinder.profile.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teacherfinder.profile.application.dto.InstitutionProfileResource;
import com.teacherfinder.profile.application.dto.UpdateInstitutionProfileResource;
import com.teacherfinder.profile.application.mapper.InstitutionProfileMapper;
import com.teacherfinder.profile.domain.service.RecruiterService;

@CrossOrigin(origins = "*", methods ={
    RequestMethod.POST,
    RequestMethod.GET,
    RequestMethod.PUT,
    RequestMethod.DELETE
})

@RestController
@RequestMapping("api/v1/recruiters")
public class RecruiterController {
    @Autowired
    RecruiterService service;

    @Autowired
    InstitutionProfileMapper institutionProfileMapper;

    @GetMapping("{recruiterId}/profiles")
    public InstitutionProfileResource getInstitutionProfileByRecruiterId(@RequestParam("recruiterId") Long recruiterId){
        return institutionProfileMapper.toResource(service.getInstitutionProfileByRecruiterId(recruiterId));
    }

    @PutMapping("{recruiterId}/profiles")
    public InstitutionProfileResource updateProfile(@RequestParam("recruiterId") Long recruiterId,UpdateInstitutionProfileResource resource){
        return institutionProfileMapper.toResource(service.updateInstitutionProfile(recruiterId, institutionProfileMapper.toModel(resource)));
    }

}
