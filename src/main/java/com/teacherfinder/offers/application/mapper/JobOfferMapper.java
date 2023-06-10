package com.teacherfinder.offers.application.mapper;

import com.teacherfinder.offers.application.dto.JobOfferResource;
import com.teacherfinder.offers.domain.model.aggregate.JobOffer;
import com.teacherfinder.offers.application.dto.CreateJobOfferResource;
import com.teacherfinder.offers.application.dto.UpdateJobOfferResource;
import com.teacherfinder.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class JobOfferMapper {
    @Autowired
    private EnhancedModelMapper modelMapper;

    public JobOffer toModel(CreateJobOfferResource dto) {
        return modelMapper.map(dto, JobOffer.class);
    }

    public JobOffer toModel(UpdateJobOfferResource dto) {
        return modelMapper.map(dto, JobOffer.class);
    }

    public JobOfferResource toResource(JobOffer model) {
        return modelMapper.map(model, JobOfferResource.class);
    }

    public List<JobOfferResource> modelListtoResource(List<JobOffer> jobOffers) {
        return modelMapper.mapList(jobOffers,JobOfferResource.class);
    }
}