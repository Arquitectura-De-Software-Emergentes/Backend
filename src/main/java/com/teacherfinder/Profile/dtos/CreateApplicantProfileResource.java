package com.teacherfinder.Profile.dtos;

import com.teacherfinder.Profile.domain.model.valueObjects.AcademicInformation;
import com.teacherfinder.Profile.domain.model.valueObjects.ContactInformation;
import com.teacherfinder.Profile.domain.model.valueObjects.PersonalInformation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateApplicantProfileResource {

    private AcademicInformation academicInformation;

    private ContactInformation contactInformation;

    private PersonalInformation personalInformation;
}
