package com.teacherfinder.applications.application.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("ApplicationMapperConfiguration")
public class MappingConfiguration {
    @Bean
    public ApplicationMapper applicationMapper(){
        return new ApplicationMapper();
    }

    @Bean
    public ApplicationApplicantProfileMapper applicationApplicantProfileMapper(){
        return new ApplicationApplicantProfileMapper();
    }
}
