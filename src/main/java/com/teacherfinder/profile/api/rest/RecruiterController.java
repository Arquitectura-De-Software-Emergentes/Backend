package com.teacherfinder.profile.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.teacherfinder.profile.application.dto.CreateRecruiterResource;
import com.teacherfinder.profile.application.dto.RecruiterResource;
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
    RecruiterMapper mapper;

    @PostMapping
    public RecruiterResource register(CreateRecruiterResource recruiterResource){
        return mapper.toResource(service.register(mapper.toModel(recruiterResource)));
    }
}
