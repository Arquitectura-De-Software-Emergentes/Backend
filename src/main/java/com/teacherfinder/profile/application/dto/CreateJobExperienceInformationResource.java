package com.teacherfinder.profile.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateJobExperienceInformationResource {

    private Long jobExperienceInformationId;
    private String company;
    private String position;
    private String time;
    private Long applicantProfileId;
}
