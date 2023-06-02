package com.teacherfinder.profile.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teacherfinder.profile.domain.model.entity.JobExperienceInformation;

@Repository
public interface JobExperienceInformationRepository extends JpaRepository<JobExperienceInformation, Long>{
    
}
