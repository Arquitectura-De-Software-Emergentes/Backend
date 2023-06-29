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

    @Bean
    public AssessmentMapper assessmentMapper(){
        return new AssessmentMapper();
    }

    @Bean
    public TestResultMapper testResultMapper(){
        return new TestResultMapper();
    }

    @Bean  VideoPresentationMapper videoPresentationMapper() { return new VideoPresentationMapper();}
}
