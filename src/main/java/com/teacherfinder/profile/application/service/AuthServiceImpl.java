package com.teacherfinder.profile.application.service;

import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.teacherfinder.profile.application.dto.AuthCredentials;
import com.teacherfinder.profile.application.dto.AuthResponse;
import com.teacherfinder.profile.application.mapper.UserMapper;
import com.teacherfinder.profile.domain.model.aggregate.User;
import com.teacherfinder.profile.domain.repository.UserRepository;
import com.teacherfinder.profile.domain.service.ApplicantService;
import com.teacherfinder.profile.domain.service.AuthService;
import com.teacherfinder.profile.domain.service.RecruiterService;
import com.teacherfinder.profile.security.middleware.TokenUtils;
import com.teacherfinder.shared.exception.ResourceNotFoundException;
import com.teacherfinder.shared.exception.ResourceValidationException;

@Service
public class AuthServiceImpl implements AuthService{

    private static final String USER = "user";
    private final UserRepository userRepository;
    private final ApplicantService applicantService;
    private final RecruiterService recruiterService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper mapper;
    private final Validator validator;

    public AuthServiceImpl(UserRepository userRepository, ApplicantService applicantService,
            RecruiterService recruiterService, AuthenticationManager authenticationManager, UserMapper mapper,
            Validator validator) {
        this.userRepository = userRepository;
        this.applicantService = applicantService;
        this.recruiterService = recruiterService;
        this.authenticationManager = authenticationManager;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    @Transactional
    public User register(User user) {
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if(!violations.isEmpty())
            throw new ResourceValidationException(USER,violations);
        
        String passwordEncoded = new BCryptPasswordEncoder().encode(user.getPassword());
        
        user = userRepository.save(user.withPassword(passwordEncoded));

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

    @Override
    public ResponseEntity<?> login(AuthCredentials credentials) {
        User user = this.getByusername(credentials.getUsername());
        AuthResponse response = new AuthResponse();

        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = TokenUtils.createToken(user.getUsername(), user.getUsername());

            response.setToken(token);
            response.setUser(mapper.toResource(user));

            return ResponseEntity.ok().body(response);

        } catch (Exception e) {
            response.setToken(e.getMessage());
            response.setUser(null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @Override
    public User getByusername(String username) {
        return this.userRepository.findOneByUsername(username)
        .orElseThrow(()-> new ResourceNotFoundException("the user with username " + username + " not found"));
    }

    @Override
    public boolean exits(Long userId) {
        return userRepository.existsById(userId);
    }
    
}
