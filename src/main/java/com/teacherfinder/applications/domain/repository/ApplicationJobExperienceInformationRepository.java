package com.teacherfinder.applications.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teacherfinder.applications.domain.model.entity.ApplicationJobExperienceInformation;

@Repository
public interface ApplicationJobExperienceInformationRepository extends JpaRepository<ApplicationJobExperienceInformation, Long> {
}
