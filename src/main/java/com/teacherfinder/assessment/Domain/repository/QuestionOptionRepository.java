package com.teacherfinder.assessment.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teacherfinder.assessment.domain.model.entity.QuestionOption;

public interface QuestionOptionRepository extends JpaRepository<QuestionOption, Long>{
    
}
