package com.teacherfinder.assessment.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teacherfinder.assessment.domain.model.entity.TestActivity;

public interface TestActivityRepository extends JpaRepository<TestActivity, Long>{
    
}
