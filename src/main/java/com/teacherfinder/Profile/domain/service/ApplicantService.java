package com.teacherfinder.Profile.domain.service;

import com.teacherfinder.Profile.domain.model.aggregate.Applicant;
import com.teacherfinder.Profile.domain.model.valueObjects.CurriculumVitae;

public interface ApplicantService {
    Applicant create(Applicant applicant);
    CurriculumVitae uploadCV(Long applicantId, CurriculumVitae cv);
    CurriculumVitae getCv(Long applicantId);
}
