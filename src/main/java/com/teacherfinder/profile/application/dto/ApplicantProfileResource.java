package com.teacherfinder.profile.application.dto;

import com.teacherfinder.profile.domain.model.valueObjects.AcademicInformation;
import com.teacherfinder.profile.domain.model.valueObjects.ContactInformation;
import com.teacherfinder.profile.domain.model.valueObjects.PersonalInformation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicantProfileResource {

    private AcademicInformation academicInformation;

    private ContactInformation contactInformation;

    private PersonalInformation personalInformation;
}
