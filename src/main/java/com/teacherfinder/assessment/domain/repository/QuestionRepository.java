package com.teacherfinder.assessment.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teacherfinder.assessment.domain.model.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long >{
    
}
