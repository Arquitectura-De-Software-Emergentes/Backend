package com.teacherfinder.offers.application.dto;

import com.teacherfinder.offers.domain.model.Enum.Experience;
import com.teacherfinder.offers.domain.model.Enum.Modality;
import com.teacherfinder.offers.domain.model.Enum.Type;
import com.teacherfinder.offers.domain.model.valueObjects.Course;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PositionProfileResource {
    private Long id;
    private String name;
    private Course course;
    private Modality modality;
    private Experience experience;
    private Type type;
}