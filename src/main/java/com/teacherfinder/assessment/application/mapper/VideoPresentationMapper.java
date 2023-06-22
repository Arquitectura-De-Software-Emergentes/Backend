package com.teacherfinder.assessment.application.mapper;

import com.teacherfinder.assessment.application.dto.CreateVideoPresentationResource;
import com.teacherfinder.assessment.application.dto.VideoPresentationResource;
import com.teacherfinder.assessment.domain.model.entity.VideoPresentation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class VideoPresentationMapper {
    @Autowired
    private ModelMapper modelMapper;

    public VideoPresentation toModel(CreateVideoPresentationResource dto) {
        return modelMapper.map(dto, VideoPresentation.class);
    }

    public VideoPresentationResource toResource(VideoPresentation model) {
        return modelMapper.map(model, VideoPresentationResource.class);
    }
}
