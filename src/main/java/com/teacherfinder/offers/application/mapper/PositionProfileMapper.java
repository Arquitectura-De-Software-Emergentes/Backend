package com.teacherfinder.offers.application.mapper;

import com.teacherfinder.offers.domain.model.entity.PositionProfile;
import com.teacherfinder.offers.application.dto.CreatePositionProfileResource;
import com.teacherfinder.offers.application.dto.PositionProfileResource;
import com.teacherfinder.offers.application.dto.UpdatePositionProfileResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PositionProfileMapper {
    @Autowired
    private ModelMapper modelMapper;

    public PositionProfile toModel(CreatePositionProfileResource dto) {
        return modelMapper.map(dto, PositionProfile.class);
    }

    public PositionProfile toModel(UpdatePositionProfileResource dto) {
        return modelMapper.map(dto, PositionProfile.class);
    }

    public PositionProfileResource toResource(PositionProfile model) {
        return modelMapper.map(model, PositionProfileResource.class);
    }
}