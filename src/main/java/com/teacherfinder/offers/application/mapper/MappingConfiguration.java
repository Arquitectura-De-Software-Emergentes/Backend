package com.teacherfinder.offers.application.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("JobOfferMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public JobOfferMapper jobOfferMapper(){
        return new JobOfferMapper();
    }
}
