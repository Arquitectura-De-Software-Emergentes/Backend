package com.teacherfinder.security.domain.service;

import com.teacherfinder.security.domain.model.aggregate.Applicant;
import com.teacherfinder.security.domain.model.valueObjects.CurriculumVitae;

public interface ApplicantService {
    Applicant create(Applicant applicant);
    void addCV(Long applicantId, CurriculumVitae cv);
    Applicant getCv(Long applicantId);
}
