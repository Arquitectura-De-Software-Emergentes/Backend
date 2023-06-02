package com.teacherfinder.profile.application.mapper;

import com.teacherfinder.profile.application.dto.CreateJobExperienceInformationResource;
import com.teacherfinder.profile.application.dto.JobExperienceInformationResource;
import com.teacherfinder.profile.domain.model.entity.JobExperienceInformation;
import com.teacherfinder.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class JobExperienceInformationMapper implements Serializable{

    @Autowired
    EnhancedModelMapper mapper;

    public JobExperienceInformation toModel(CreateJobExperienceInformationResource dto) {
            return mapper.map(dto, JobExperienceInformation.class);
    }
    public JobExperienceInformationResource toResource(JobExperienceInformation model) {
            return mapper.map(model, JobExperienceInformationResource.class);
    }
}
