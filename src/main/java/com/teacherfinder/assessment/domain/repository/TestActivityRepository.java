package com.teacherfinder.assessment.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teacherfinder.assessment.domain.model.entity.TestActivity;

public interface TestActivityRepository extends JpaRepository<TestActivity, Long>{
    Optional<TestActivity> findByAssessmentAssessmentId(Long assessmentId);
    List<TestActivity> findByRecruiterId(Long assessmentId);
}
