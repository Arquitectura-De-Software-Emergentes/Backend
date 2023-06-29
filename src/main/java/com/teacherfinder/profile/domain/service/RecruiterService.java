package com.teacherfinder.profile.domain.service;

import com.teacherfinder.profile.domain.model.aggregate.User;
import com.teacherfinder.profile.domain.model.entity.InstitutionProfile;

public interface RecruiterService {
    InstitutionProfile getInstitutionProfileByRecruiterId(Long recruiterId);
    InstitutionProfile updateInstitutionProfile(Long recruiterId, InstitutionProfile institutionProfile);
    void generateProfile(User recruiter);
    Boolean exist(long recruiterId);
}
