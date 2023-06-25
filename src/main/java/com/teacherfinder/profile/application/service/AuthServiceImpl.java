package com.teacherfinder.profile.application.service;

import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.stereotype.Service;

import com.teacherfinder.profile.domain.model.aggregate.User;
import com.teacherfinder.profile.domain.repository.UserRepository;
import com.teacherfinder.profile.domain.service.ApplicantService;
import com.teacherfinder.profile.domain.service.AuthService;
import com.teacherfinder.profile.domain.service.RecruiterService;
import com.teacherfinder.shared.exception.ResourceValidationException;

@Service
public class AuthServiceImpl implements AuthService{

    private static final String USER = "user";
    private final UserRepository userRepository;
    private final ApplicantService applicantService;
    private final RecruiterService recruiterService;
    private final Validator validator;

    public AuthServiceImpl(UserRepository userRepository, ApplicantService applicantService,
            RecruiterService recruiterService, Validator validator) {
        this.userRepository = userRepository;
        this.applicantService = applicantService;
        this.recruiterService = recruiterService;
        this.validator = validator;
    }

    @Override
    @Transactional
    public User register(User user) {
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if(!violations.isEmpty())
            throw new ResourceValidationException(USER,violations);
        
        user = userRepository.save(user);

        switch (user.getRole()) {
            case APPLICANT:
                applicantService.createProfile(user);
                break;
            case RECRUITER:
                recruiterService.generateProfile(user);
                break;
            default:
                throw new ResourceValidationException("Invalid User Role");
        }

        return user;
    }
    
}
