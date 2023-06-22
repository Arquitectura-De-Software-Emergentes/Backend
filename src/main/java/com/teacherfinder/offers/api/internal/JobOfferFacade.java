package com.teacherfinder.offers.api.internal;

import com.teacherfinder.offers.application.dto.JobOfferResource;
import com.teacherfinder.offers.application.mapper.JobOfferMapper;
import com.teacherfinder.offers.domain.service.JobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class JobOfferFacade {

    @Autowired
    JobOfferMapper jobOfferMapper;

    @Autowired
    JobOfferService jobOfferService;
    public JobOfferResource getJobOfferById(Long offerId) {
        return jobOfferMapper.toResource(jobOfferService.getJobOfferById(offerId));

    }
}
