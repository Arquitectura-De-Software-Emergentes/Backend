package com.teacherfinder.security.api.dtos;

import com.teacherfinder.applications.domain.model.valueObjects.ApplicantId;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicantResource {
    private ApplicantId applicantId;
}
