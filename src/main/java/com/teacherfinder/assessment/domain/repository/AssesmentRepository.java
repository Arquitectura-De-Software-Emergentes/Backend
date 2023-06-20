package com.teacherfinder.assessment.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teacherfinder.assessment.domain.model.aggregate.Assessment;

public interface AssesmentRepository extends JpaRepository<Assessment, Long>{

    Optional<Assessment> findByJobOfferId(Long jobOfferId);
}
