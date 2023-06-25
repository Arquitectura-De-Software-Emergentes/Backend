package com.teacherfinder.profile.domain.factory;

import org.springframework.stereotype.Component;

import com.teacherfinder.profile.domain.model.aggregate.User;
import com.teacherfinder.profile.domain.model.entity.InstitutionProfile;

@Component
public class InstitutionProfileFactory {

    private static final String EMPTY = "";

    public InstitutionProfile generateInstitutionProfile(User recruiter){
        return new InstitutionProfile(recruiter.getUserId(),EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,recruiter);
    }

}
