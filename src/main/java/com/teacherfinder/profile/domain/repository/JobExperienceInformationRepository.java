package com.teacherfinder.profile.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.teacherfinder.profile.domain.model.entity.JobExperienceInformation;

public interface JobExperienceInformationRepository extends JpaRepository<JobExperienceInformation, Long>{
    
}
