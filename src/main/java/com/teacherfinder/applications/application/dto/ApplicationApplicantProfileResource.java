package com.teacherfinder.applications.application.dto;

import com.teacherfinder.applications.domain.model.valueObjects.AcademicInformation;
import com.teacherfinder.applications.domain.model.valueObjects.ContactInformation;
import com.teacherfinder.applications.domain.model.valueObjects.PersonalInformation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationApplicantProfileResource {
    private Long applicantProfileId;
    private AcademicInformation academicInformation;
    private ContactInformation contactInformation;
    private PersonalInformation personalInformation;
}
