package com.teacherfinder.offers.dto;

import com.teacherfinder.offers.domain.model.Enum.Modality;
import com.teacherfinder.offers.domain.model.Enum.Type;
import com.teacherfinder.offers.domain.model.valueObjects.Course;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePositionProfileResource {
    private Course course;
    private Modality experience;
    private Type type;
}