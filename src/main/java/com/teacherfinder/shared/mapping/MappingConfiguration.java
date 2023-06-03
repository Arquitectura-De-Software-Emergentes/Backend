package com.teacherfinder.shared.mapping;

import com.teacherfinder.profile.application.mapper.ApplicantProfileMapper;
import com.teacherfinder.profile.application.mapper.JobExperienceInformationMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.teacherfinder.applications.application.mapper.ApplicationMapper;
import com.teacherfinder.profile.application.mapper.ApplicantMapper;

@Configuration("Configuration")
public class MappingConfiguration {

    @Bean
    public EnhancedModelMapper modelMapper(){
        return new EnhancedModelMapper();
    }

    @Bean
    public ApplicantMapper applicantMapper(){
        return new ApplicantMapper();
    }

    @Bean
    public ApplicantProfileMapper applicantProfileMapper(){
        return new ApplicantProfileMapper();
    }

    @Bean
    public JobExperienceInformationMapper jobExperienceInformationMapper(){
        return new JobExperienceInformationMapper();
    }

    @Bean
    public ApplicationMapper applicationMapper(){
        return new ApplicationMapper();
    }
}
