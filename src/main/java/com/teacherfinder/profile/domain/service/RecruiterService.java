package com.teacherfinder.profile.domain.service;

import com.teacherfinder.profile.domain.model.aggregate.Recruiter;
import com.teacherfinder.profile.domain.model.entity.InstitutionProfile;

public interface RecruiterService {
    Recruiter register(Recruiter recruiter);
    InstitutionProfile updateInstitutionProfile(Long recruiterId, InstitutionProfile institutionProfile);
}
