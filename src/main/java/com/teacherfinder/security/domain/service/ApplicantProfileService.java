package com.teacherfinder.security.domain.service;

import com.teacherfinder.security.domain.model.aggregate.Applicant;

public interface ApplicantProfileService {
    void createProfile(Applicant applicant);
}
