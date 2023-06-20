package com.teacherfinder.assessment.domain.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.teacherfinder.assessment.domain.model.entity.TestResult;
import com.teacherfinder.assessment.domain.model.valueObject.TestResultId;

public interface TestResultRepository extends JpaRepository<TestResult,TestResultId> {
    Optional<TestResult> findByTestResultId(TestResultId testResultId);
}
