package com.teacherfinder.profile.domain.factory;

import org.springframework.stereotype.Component;

import com.teacherfinder.profile.domain.model.aggregate.Recruiter;
import com.teacherfinder.profile.domain.model.entity.InstitutionProfile;

@Component
public class InstitutionProfileFactory {

    private static final String EMPTY = "";

    public InstitutionProfile generateInstitutionProfile(Recruiter recruiter){
        return new InstitutionProfile(recruiter.getRecruiterId(),EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,recruiter);
    }

}
