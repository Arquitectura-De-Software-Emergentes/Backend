package com.teacherfinder.offers.api.rest;

import com.teacherfinder.offers.domain.model.aggregate.JobOffer;
import com.teacherfinder.offers.domain.model.aggregate.PositionProfile;
import com.teacherfinder.offers.domain.model.services.JobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/job-offers")
public class JobOfferController {
   @Autowired
    private  JobOfferService jobOfferService;



    @PostMapping
    public ResponseEntity<JobOffer> createJobOffer(@RequestBody JobOffer jobOffer) {
        JobOffer createdJobOffer = jobOfferService.createJobOffer(jobOffer);
        return ResponseEntity.ok(createdJobOffer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobOffer> getJobOfferById(@PathVariable Long id) {
        JobOffer createJobOffer = jobOfferService.getJobOfferById(id);
        if (createJobOffer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(createJobOffer);
    }

    @PostMapping("/position-profiles")
    public ResponseEntity<PositionProfile> createPositionProfile(@RequestBody PositionProfile positionProfile) {
        PositionProfile createdPositionProfile = jobOfferService.createPositionProfile(positionProfile);
        return ResponseEntity.ok(createdPositionProfile);
    }
}
