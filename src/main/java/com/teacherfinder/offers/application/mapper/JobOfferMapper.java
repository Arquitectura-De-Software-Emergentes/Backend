package com.teacherfinder.offers.application.mapper;

import com.teacherfinder.offers.application.dto.JobOfferResource;
import com.teacherfinder.offers.domain.model.aggregate.JobOffer;
import com.teacherfinder.offers.application.dto.CreateJobOfferResource;
import com.teacherfinder.offers.application.dto.UpdateJobOfferResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobOfferMapper {
    @Autowired
    private ModelMapper modelMapper;

    public JobOffer toModel(CreateJobOfferResource dto) {
        return modelMapper.map(dto, JobOffer.class);
    }

    public JobOffer toModel(UpdateJobOfferResource dto) {
        return modelMapper.map(dto, JobOffer.class);
    }

    public JobOfferResource toResource(JobOffer model) {
        return modelMapper.map(model, JobOfferResource.class);
    }
}