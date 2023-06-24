package com.teacherfinder.profile.application.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.teacherfinder.profile.application.dto.InstitutionProfileResource;
import com.teacherfinder.profile.application.dto.UpdateInstitutionProfileResource;
import com.teacherfinder.profile.domain.model.entity.InstitutionProfile;
import com.teacherfinder.shared.mapping.EnhancedModelMapper;

public class InstitutionProfileMapper {
    @Autowired
    EnhancedModelMapper mapper;

    public InstitutionProfile toModel(UpdateInstitutionProfileResource resource){
        return mapper.map(resource, InstitutionProfile.class);
    }

    public InstitutionProfileResource toResource(InstitutionProfile model){
        return mapper.map(model, InstitutionProfileResource.class);
    }
}
