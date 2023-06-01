package com.teacherfinder.Profile.domain.service;

import com.teacherfinder.Profile.domain.model.aggregate.Applicant;

public interface ApplicantProfileService {
    void createProfile(Applicant applicant);
}
