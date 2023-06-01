package com.teacherfinder.profile.domain.service;

import com.teacherfinder.profile.domain.model.aggregate.Applicant;
import com.teacherfinder.profile.domain.model.aggregate.ApplicantProfile;
import com.teacherfinder.profile.domain.model.valueObjects.CurriculumVitae;

public interface ApplicantService {
    Applicant create(Applicant applicant);
    CurriculumVitae uploadCV(Long applicantId, CurriculumVitae cv);
    CurriculumVitae getCv(Long applicantId);
    ApplicantProfile updateProfile(ApplicantProfile profile);
}
