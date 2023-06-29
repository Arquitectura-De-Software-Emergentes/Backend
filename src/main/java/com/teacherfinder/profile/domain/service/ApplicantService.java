package com.teacherfinder.profile.domain.service;

import com.teacherfinder.profile.domain.model.aggregate.User;
import com.teacherfinder.profile.domain.model.entity.ApplicantProfile;
import com.teacherfinder.profile.domain.model.entity.JobExperienceInformation;
import com.teacherfinder.profile.domain.model.valueObjects.CurriculumVitae;

public interface ApplicantService {
    CurriculumVitae uploadCV(Long applicantId, CurriculumVitae cv);
    CurriculumVitae getCv(Long applicantId);
    ApplicantProfile updateProfile(Long applicantId, ApplicantProfile profile);
    JobExperienceInformation addJobExperience(JobExperienceInformation experience);
    ApplicantProfile getApplicantProfile(Long applicantId);
    void createProfile(User applicant);
    Boolean exist(Long userId);
}
