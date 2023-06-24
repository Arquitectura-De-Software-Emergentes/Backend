package com.teacherfinder.profile.application.service;

import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.stereotype.Service;

import com.teacherfinder.profile.domain.factory.InstitutionProfileFactory;
import com.teacherfinder.profile.domain.model.aggregate.Recruiter;
import com.teacherfinder.profile.domain.model.entity.InstitutionProfile;
import com.teacherfinder.profile.domain.repository.InstitutionProfileRepository;
import com.teacherfinder.profile.domain.repository.RecruiterRepository;
import com.teacherfinder.profile.domain.service.RecruiterService;
import com.teacherfinder.shared.exception.ResourceValidationException;

@Service
public class RecruiterServiceImpl implements RecruiterService{

    private static final String RECRUITER = "recruiter";
    private final RecruiterRepository recruiterRepository;
    private final InstitutionProfileRepository institutionProfileRepository;
    private final InstitutionProfileFactory profileFactory;
    private final Validator validator;


    public RecruiterServiceImpl(RecruiterRepository recruiterRepository,
            InstitutionProfileRepository institutionProfileRepository, InstitutionProfileFactory profileFactory,
            Validator validator) {
        this.recruiterRepository = recruiterRepository;
        this.institutionProfileRepository = institutionProfileRepository;
        this.profileFactory = profileFactory;
        this.validator = validator;
    }

    @Override
    @Transactional
    public Recruiter register(Recruiter recruiter) {
        Set<ConstraintViolation<Recruiter>> violations = validator.validate(recruiter);

        if(!violations.isEmpty())
            throw new ResourceValidationException(RECRUITER, violations);
        
        recruiter = recruiterRepository.save(recruiter);

        generateProfile(recruiter);

        return recruiter;
    }

    private void generateProfile(Recruiter recruiter){
        InstitutionProfile profile = profileFactory.generateInstitutionProfile(recruiter);
        institutionProfileRepository.save(profile);
    }
    
}
