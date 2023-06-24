package com.teacherfinder.profile.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teacherfinder.profile.application.dto.CreateRecruiterResource;
import com.teacherfinder.profile.application.dto.InstitutionProfileResource;
import com.teacherfinder.profile.application.dto.RecruiterResource;
import com.teacherfinder.profile.application.dto.UpdateInstitutionProfileResource;
import com.teacherfinder.profile.application.mapper.InstitutionProfileMapper;
import com.teacherfinder.profile.application.mapper.RecruiterMapper;
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
    RecruiterMapper recruiterMapper;

    @Autowired
    InstitutionProfileMapper institutionProfileMapper;

    @PostMapping
    public RecruiterResource register(CreateRecruiterResource recruiterResource){
        return recruiterMapper.toResource(service.register(recruiterMapper.toModel(recruiterResource)));
    }

    @PutMapping("{recruiterId}/profiles")
    public InstitutionProfileResource updateProfile(@RequestParam("recruiterId") Long recruiterId,UpdateInstitutionProfileResource resource){
        return institutionProfileMapper.toResource(service.updateInstitutionProfile(recruiterId, institutionProfileMapper.toModel(resource)));
    }
}
