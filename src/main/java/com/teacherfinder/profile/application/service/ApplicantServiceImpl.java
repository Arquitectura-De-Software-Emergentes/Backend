package com.teacherfinder.profile.application.service;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.teacherfinder.profile.domain.model.Enum.Role;
import com.teacherfinder.profile.domain.repository.ApplicantProfileRepository;
import org.springframework.stereotype.Service;

import com.teacherfinder.profile.domain.factory.ApplicantProfileFactory;
import com.teacherfinder.profile.domain.model.aggregate.User;
import com.teacherfinder.profile.domain.model.entity.ApplicantProfile;
import com.teacherfinder.profile.domain.model.entity.JobExperienceInformation;
import com.teacherfinder.profile.domain.model.valueObjects.CurriculumVitae;
import com.teacherfinder.profile.domain.repository.JobExperienceInformationRepository;
import com.teacherfinder.profile.domain.repository.UserRepository;
import com.teacherfinder.profile.domain.service.ApplicantService;
import com.teacherfinder.shared.exception.ResourceNotFoundException;
import com.teacherfinder.shared.exception.ResourceValidationException;

@Service
public class ApplicantServiceImpl implements ApplicantService {

    private static final String APPLICANT = "applicant";
    private static final String PROFILE = "profile";
    private final UserRepository userRepository;
    private final ApplicantProfileRepository profileRepository;
    private final JobExperienceInformationRepository experienceRepository;
    private final ApplicantProfileFactory profileFactory;
    private final Validator validator;

    public ApplicantServiceImpl(UserRepository userRepository, ApplicantProfileRepository profileRepository,
            JobExperienceInformationRepository experienceRepository, ApplicantProfileFactory profileFactory,
            Validator validator) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.experienceRepository = experienceRepository;
        this.profileFactory = profileFactory;
        this.validator = validator;
    }

    @Override
    public CurriculumVitae uploadCV(Long applicantId, CurriculumVitae cv) {

        User applicant = userRepository.findById(applicantId)
                .orElseThrow(() -> new ResourceNotFoundException(APPLICANT, applicantId));

        applicant.setCv(cv);

        return userRepository.save(applicant).getCv();
    }

    @Override
    public CurriculumVitae getCv(Long applicantId) {
        User currentApplicant = userRepository.findById(applicantId)
                .orElseThrow(() -> new ResourceNotFoundException(APPLICANT, applicantId));

        if (currentApplicant.getCv() == null) 
            throw new ResourceNotFoundException("The CV of the User with id " + applicantId + " not found");

        return currentApplicant.getCv();
    }

    @Override
    public ApplicantProfile updateProfile( Long applicantId,ApplicantProfile profile) {
        Set<ConstraintViolation<ApplicantProfile>> violations = validator.validate(profile);

        if (!violations.isEmpty())
            throw new ResourceValidationException(PROFILE, violations);
        return profileRepository.findById(applicantId).map(
            profileDb -> profileRepository.save(profileDb
                .withAcademicInformation(profile.getAcademicInformation())
                .withContactInformation(profile.getContactInformation())
                .withPersonalInformation(profile.getPersonalInformation()))
            ).orElseThrow(() -> new ResourceNotFoundException(APPLICANT, applicantId));
    }

    @Override
    public JobExperienceInformation addJobExperience(JobExperienceInformation experience) {

        Set<ConstraintViolation<JobExperienceInformation>> violations = validator.validate(experience);

        if (!violations.isEmpty())
            throw new ResourceValidationException(APPLICANT, violations);

        return experienceRepository.save(experience);
    }

    @Override
    public ApplicantProfile getApplicantProfile(Long applicantId) {
        return profileRepository.findByApplicantUserId(applicantId)
            .orElseThrow(()->new ResourceNotFoundException(PROFILE,applicantId));
    }

    @Override
    public void createProfile(User applicant){
        ApplicantProfile profile = profileFactory.createProfile(applicant);
        profileRepository.save(profile);
    }

    @Override
    public Boolean exist(Long userId) {
        return userRepository.existsByUserIdAndRole(userId, Role.APPLICANT);
    }

}
