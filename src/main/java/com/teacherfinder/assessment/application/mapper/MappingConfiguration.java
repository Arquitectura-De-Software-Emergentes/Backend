package com.teacherfinder.assessment.application.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("AssementMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public TestActivityMapper testActivityMapper(){
        return new TestActivityMapper();
    }
}
