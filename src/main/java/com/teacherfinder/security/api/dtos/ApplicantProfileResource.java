package com.teacherfinder.security.api.dtos;

import com.teacherfinder.security.domain.model.valueObjects.AcademicInformation;
import com.teacherfinder.security.domain.model.valueObjects.ApplicantProfileId;
import com.teacherfinder.security.domain.model.valueObjects.ContactInformation;
import com.teacherfinder.security.domain.model.valueObjects.PersonalInformation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicantProfileResource {
    private ApplicantProfileId applicantProfileId;

    private AcademicInformation academicInformation;

    private ContactInformation contactInformation;

    private PersonalInformation personalInformation;
}
