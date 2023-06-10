package com.teacherfinder.profile.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobExperienceInformationResource {

    private Long jobExperienceInformationId;
    private String company;
    private String position;
    private String time;
}
