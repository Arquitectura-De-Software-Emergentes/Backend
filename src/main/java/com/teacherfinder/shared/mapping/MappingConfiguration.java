package com.teacherfinder.shared.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("Configuration")
public class MappingConfiguration {

    @Bean
    public EnhancedModelMapper modelMapper(){
        return new EnhancedModelMapper();
    }
}
