package com.teacherfinder.applications.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.teacherfinder.applications.domain.model.entity.ApplicationJobExperienceInformation;

public interface ApplicationJobExperienceInformationRepository extends JpaRepository<ApplicationJobExperienceInformation, Long> {
}
