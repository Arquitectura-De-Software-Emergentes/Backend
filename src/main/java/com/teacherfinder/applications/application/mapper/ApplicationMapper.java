package com.teacherfinder.applications.application.mapper;

import java.io.Serializable;
import java.util.List;

import com.teacherfinder.applications.application.dto.GetApplicationByApplicantIdResource;
import com.teacherfinder.applications.domain.model.entity.JobOffer;
import com.teacherfinder.offers.application.dto.JobOfferResource;
import org.springframework.beans.factory.annotation.Autowired;

import com.teacherfinder.applications.application.dto.GetApplicationByOfferIdResource;
import com.teacherfinder.applications.domain.model.aggregate.Application;
import com.teacherfinder.shared.mapping.EnhancedModelMapper;

public class ApplicationMapper implements Serializable{
    
    @Autowired
    EnhancedModelMapper mapper;

    public List<GetApplicationByOfferIdResource> modelListToResourceByOfferId(List<Application> model){
        return mapper.mapList(model, GetApplicationByOfferIdResource.class);
    }

    public List<GetApplicationByApplicantIdResource> modelListToResourceByApplicantId(List<Application> model){
        return mapper.mapList(model, GetApplicationByApplicantIdResource.class);
    }

    public JobOffer jobOfferFacadeToModel(JobOfferResource facadeResource){
        return mapper.map(facadeResource, JobOffer.class);
    }
}
