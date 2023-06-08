package com.teacherfinder.applications.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.teacherfinder.applications.application.dto.ApplicationResource;
import com.teacherfinder.applications.application.dto.ApplyResource;
import com.teacherfinder.applications.application.mapper.ApplicationMapper;
import com.teacherfinder.applications.domain.service.ApplicationService;

@CrossOrigin(origins = "*", methods = {
    RequestMethod.POST,
    RequestMethod.GET,
    RequestMethod.PUT
})
@RestController
@RequestMapping("api/v1/application")
public class ApplicationController {
    
    @Autowired
    ApplicationService service;

    @Autowired
    ApplicationMapper mapper;

    @PostMapping
    public ApplicationResource apply(@RequestBody ApplyResource applyResource){
        return mapper.toResource(service.apply(mapper.toApplicationId(applyResource)));
    }
}
