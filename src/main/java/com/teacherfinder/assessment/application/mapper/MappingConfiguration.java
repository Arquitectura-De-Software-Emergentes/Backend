package com.teacherfinder.assessment.application.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("AssementMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public TestActivityMapper testActivityMapper(){
        return new TestActivityMapper();
    }

    @Bean
    public QuestionMapper questionMapper(){
        return new QuestionMapper();
    }

    @Bean
    public QuestionOptionMapper questionOptionMapper(){
        return new QuestionOptionMapper();
    }
}
