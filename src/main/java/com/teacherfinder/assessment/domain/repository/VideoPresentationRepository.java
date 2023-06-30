package com.teacherfinder.assessment.domain.repository;

import com.teacherfinder.assessment.domain.model.entity.VideoPresentation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;


public interface VideoPresentationRepository extends JpaRepository<VideoPresentation, Long> {
    Optional<VideoPresentation> findByAssessmentAssessmentIdAndApplicantId(Long assessmentId, Long applicantId);

    List<VideoPresentation> findByApplicantId(Long applicantId);
}
