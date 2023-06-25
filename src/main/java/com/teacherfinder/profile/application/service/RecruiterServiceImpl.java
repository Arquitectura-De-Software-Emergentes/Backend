package com.teacherfinder.profile.application.service;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.stereotype.Service;

import com.teacherfinder.profile.domain.factory.InstitutionProfileFactory;
import com.teacherfinder.profile.domain.model.aggregate.User;
import com.teacherfinder.profile.domain.model.entity.InstitutionProfile;
import com.teacherfinder.profile.domain.repository.InstitutionProfileRepository;
import com.teacherfinder.profile.domain.repository.UserRepository;
import com.teacherfinder.profile.domain.service.RecruiterService;
import com.teacherfinder.shared.exception.ResourceNotFoundException;
import com.teacherfinder.shared.exception.ResourceValidationException;

@Service
public class RecruiterServiceImpl implements RecruiterService {

    private static final String INSTITUTION_PROFILE = "institution profile";
    private final InstitutionProfileRepository institutionProfileRepository;
    private final InstitutionProfileFactory profileFactory;
    private final Validator validator;

    public RecruiterServiceImpl(UserRepository userRepository,
            InstitutionProfileRepository institutionProfileRepository, InstitutionProfileFactory profileFactory,
            Validator validator) {
        this.institutionProfileRepository = institutionProfileRepository;
        this.profileFactory = profileFactory;
        this.validator = validator;
    }

    @Override
    public InstitutionProfile updateInstitutionProfile(Long recruiterId, InstitutionProfile institutionProfile) {
        Set<ConstraintViolation<InstitutionProfile>> violations = validator.validate(institutionProfile);

        if(!violations.isEmpty())
            throw new ResourceValidationException(INSTITUTION_PROFILE, violations);

        return institutionProfileRepository.findById(recruiterId).map(
            profile -> institutionProfileRepository.save(profile
            .withAddress(institutionProfile.getAddress())
            .withDescription(institutionProfile.getDescription())
            .withImage(institutionProfile.getImage())
            .withName(institutionProfile.getName())
            .withPhoneNumber(institutionProfile.getPhoneNumber())
            .withUrlWebPage(institutionProfile.getUrlWebPage()))
        ).orElseThrow(()-> new ResourceNotFoundException(INSTITUTION_PROFILE, recruiterId));
    }

    @Override
    public InstitutionProfile getInstitutionProfileByRecruiterId(Long recruiterId) {
        return institutionProfileRepository.findById(recruiterId)
            .orElseThrow(()-> new ResourceNotFoundException(INSTITUTION_PROFILE, recruiterId));   
    }

    @Override
    public void generateProfile(User recruiter) {
        InstitutionProfile profile = profileFactory.generateInstitutionProfile(recruiter);
        institutionProfileRepository.save(profile);
    }
}
