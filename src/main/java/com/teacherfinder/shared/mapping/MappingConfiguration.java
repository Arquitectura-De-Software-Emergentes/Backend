package com.teacherfinder.shared.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.teacherfinder.security.api.mapper.ApplicantMapper;

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
}
