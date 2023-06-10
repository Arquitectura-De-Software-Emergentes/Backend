package com.teacherfinder.profile.application.mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("ProfileMappingConfiguration")
public class MappingConfiguration {
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
}
