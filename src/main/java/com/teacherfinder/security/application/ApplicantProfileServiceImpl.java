package com.teacherfinder.security.application;

import org.springframework.stereotype.Service;

import com.teacherfinder.security.domain.model.aggregate.Applicant;
import com.teacherfinder.security.domain.model.aggregate.ApplicantProfile;
import com.teacherfinder.security.domain.repository.ApplicantProfileRepository;
import com.teacherfinder.security.domain.service.ApplicantProfileService;

@Service
public class ApplicantProfileServiceImpl implements ApplicantProfileService{

    private final ApplicantProfileRepository profileRepository;

    public ApplicantProfileServiceImpl(ApplicantProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public void createProfile(Applicant applicant) {
        
        ApplicantProfile profile = new ApplicantProfile();

        profileRepository.save(profile.withApplicant(applicant));
    }
    
}
