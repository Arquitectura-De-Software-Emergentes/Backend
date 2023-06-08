package com.teacherfinder.offers.application.dto;

import com.teacherfinder.offers.domain.model.Enum.Modality;
import com.teacherfinder.offers.domain.model.Enum.Type;
import com.teacherfinder.offers.domain.model.valueObjects.Course;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePositionProfileResource {
    private Course course;
    private Modality experience;
    private Type type;
}