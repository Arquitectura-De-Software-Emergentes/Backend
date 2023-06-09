package com.teacherfinder.profile.api.rest;

import com.teacherfinder.profile.domain.model.Enum.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.teacherfinder.profile.application.dto.ApplicantResource;
import com.teacherfinder.profile.application.dto.AuthCredentials;
import com.teacherfinder.profile.application.dto.CreateApplicantResource;
import com.teacherfinder.profile.application.dto.CreateRecruiterResource;
import com.teacherfinder.profile.application.dto.RecruiterResource;
import com.teacherfinder.profile.application.mapper.ApplicantMapper;
import com.teacherfinder.profile.application.mapper.RecruiterMapper;
import com.teacherfinder.profile.domain.service.AuthService;

@CrossOrigin(origins = "*", methods ={
    RequestMethod.POST,
    RequestMethod.GET,
    RequestMethod.PUT,
    RequestMethod.DELETE
})

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    AuthService service;

    @Autowired
    ApplicantMapper applicantMapper;

    @Autowired
    RecruiterMapper recruiterMapper;

    @PostMapping("/recruiters/register")
    public RecruiterResource registerRecruiter(@RequestBody CreateRecruiterResource resource){
        resource.setRole(Role.RECRUITER);
        return recruiterMapper.toResource(service.register(recruiterMapper.toModel(resource)));
    }

    @PostMapping("/applicants/register")
    public ApplicantResource registerApplicant(@RequestBody CreateApplicantResource resource){
        resource.setRole(Role.APPLICANT);
        return applicantMapper.toResource(service.register(applicantMapper.toModel(resource)));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody AuthCredentials credentials) {

        return service.login(credentials);

    }
}
