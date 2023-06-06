package com.teacherfinder.offers.api.rest;

import com.teacherfinder.offers.domain.model.aggregate.JobOffer;
import com.teacherfinder.offers.domain.model.aggregate.PositionProfile;
import com.teacherfinder.offers.domain.model.services.JobOfferService;
import com.teacherfinder.offers.dto.CreateJobOfferResource;
import com.teacherfinder.offers.dto.CreatePositionProfileResource;
import com.teacherfinder.offers.dto.JobOfferResource;
import com.teacherfinder.offers.dto.PositionProfileResource;
import com.teacherfinder.offers.mapper.JobOfferMapper;
import com.teacherfinder.offers.mapper.PositionProfileMapper;
import com.teacherfinder.shared.exception.ResourceNotFoundException;
import com.teacherfinder.shared.exception.ResourceValidationException;
import lombok.extern.flogger.Flogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/job-offers")
public class JobOfferController {
   @Autowired
    private  JobOfferService jobOfferService;

   @Autowired
    private JobOfferMapper jobOfferMapper;

   @Autowired
    private PositionProfileMapper positionProfileMapper;



    @PostMapping
    public JobOfferResource createJobOffer(@RequestParam(name = "recruiterId") Long recruiterId,@RequestBody CreateJobOfferResource createJobOfferResource) {
        return jobOfferMapper.toResource(jobOfferService.createJobOffer(recruiterId,jobOfferMapper.toModel(createJobOfferResource)));
    }


    @GetMapping("/{id}")
    public JobOfferResource getJobOfferById(@PathVariable Long id) {
        return jobOfferMapper.toResource(jobOfferService.getJobOfferById(id));

    }

    @PostMapping("/position-profiles")
    public PositionProfileResource createPositionProfile(@RequestBody CreatePositionProfileResource positionProfile) {
        return positionProfileMapper.toResource(jobOfferService.createPositionProfile(positionProfileMapper.toModel(positionProfile)));

    }
}
