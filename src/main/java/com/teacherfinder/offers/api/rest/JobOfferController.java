package com.teacherfinder.offers.api.rest;

import com.teacherfinder.offers.domain.service.JobOfferService;
import com.teacherfinder.offers.application.dto.CreateJobOfferResource;
import com.teacherfinder.offers.application.dto.JobOfferResource;
import com.teacherfinder.offers.application.dto.PositionProfileResource;
import com.teacherfinder.offers.application.dto.UpdatePositionProfileResource;
import com.teacherfinder.offers.application.mapper.JobOfferMapper;
import com.teacherfinder.offers.application.mapper.PositionProfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", methods ={
        RequestMethod.POST,
        RequestMethod.GET,
        RequestMethod.PUT,
        RequestMethod.DELETE
})
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
    public JobOfferResource createJobOffer(@RequestBody CreateJobOfferResource createJobOfferResource) {
        return jobOfferMapper.toResource(jobOfferService.createJobOffer(jobOfferMapper.toModel(createJobOfferResource)));
    }

    @PostMapping("{jobOfferId}/apply/{applicantId}")
    public ResponseEntity<String> apply(@PathVariable("jobOfferId") Long jobOfferId, @PathVariable("applicantId") Long applicantId){
        return jobOfferService.apply(jobOfferId, applicantId);
    }

    @GetMapping("/{id}")
    public JobOfferResource getJobOfferById(@PathVariable("id") Long id) {
        return jobOfferMapper.toResource(jobOfferService.getJobOfferById(id));

    }

    @GetMapping
    public List<JobOfferResource> getAll(){
        return jobOfferMapper.modelListtoResource(jobOfferService.getAllJobOffer());
    }

    @GetMapping("/recruiter/{recruiterId}")
    public  List<JobOfferResource> getAllByRecruiterId(@PathVariable("recruiterId") Long recruiterId){
        return  jobOfferMapper.modelListtoResource(jobOfferService.getAllJobOfferByRecruiterId(recruiterId));
    }

    @PutMapping("/position-profiles/{positionProfileId}")
    public PositionProfileResource updatePostionProifile(@PathVariable("positionProfileId") Long positionProfileId, @RequestBody UpdatePositionProfileResource resource){
        return positionProfileMapper.toResource(jobOfferService.updatePositionProfile(positionProfileId, positionProfileMapper.toModel(resource)));
    }

    @PutMapping("/{jobOfferId}/enable")
    public ResponseEntity<String> enablePostionProifile(@PathVariable("jobOfferId") Long jobOfferId){
        return jobOfferService.enable(jobOfferId);
    }

    @PutMapping("/{jobOfferId}/disable")
    public ResponseEntity<String> disablePostionProifile(@PathVariable("jobOfferId") Long jobOfferId){
        return jobOfferService.disable(jobOfferId);
    }
}
