package com.teacherfinder.applications.application.dto;

import com.teacherfinder.applications.domain.model.valueObjects.ApplicationId;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetApplicationByOfferIdResource {
    ApplicationId applicationId;
    private String status;
    private ApplicationApplicantProfileResource applicantProfile;
}
