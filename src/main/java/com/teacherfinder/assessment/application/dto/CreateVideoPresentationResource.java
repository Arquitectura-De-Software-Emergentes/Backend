package com.teacherfinder.assessment.application.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateVideoPresentationResource {
    Double duration;
    String description;
    String urlVideo;
}
