package com.teacherfinder.profile.domain.factory;

import com.teacherfinder.profile.domain.model.valueObjects.AcademicInformation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.teacherfinder.profile.domain.model.aggregate.User;
import com.teacherfinder.profile.domain.model.entity.ApplicantProfile;
import com.teacherfinder.profile.domain.model.entity.JobExperienceInformation;
import com.teacherfinder.profile.domain.model.valueObjects.ContactInformation;
import com.teacherfinder.profile.domain.model.valueObjects.PersonalInformation;

@Component
public class ApplicantProfileFactory {

    private static final String EMPTY = ""; 
    private static final Date NOW = new Date();

    public ApplicantProfile createProfile(User applicant){
        AcademicInformation academicInformation = new AcademicInformation(EMPTY,EMPTY,EMPTY);
        ContactInformation contactInformation = new ContactInformation(EMPTY,EMPTY,EMPTY);
        PersonalInformation personalInformation = new PersonalInformation(EMPTY,EMPTY,EMPTY,NOW,EMPTY);
        List<JobExperienceInformation> jobExperienceInformations = new ArrayList<JobExperienceInformation>();

        return new ApplicantProfile(applicant.getUserId(), academicInformation, contactInformation, personalInformation, jobExperienceInformations, applicant);
    }
}
