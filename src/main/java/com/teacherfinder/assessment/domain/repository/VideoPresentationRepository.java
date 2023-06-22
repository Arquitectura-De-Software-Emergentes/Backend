package com.teacherfinder.assessment.domain.repository;

import com.teacherfinder.assessment.domain.model.entity.VideoPresentation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VideoPresentationRepository extends JpaRepository<VideoPresentation, Long> {
    Optional<VideoPresentation> findById(Long id);
}
