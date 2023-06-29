package com.teacherfinder.applications.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.teacherfinder.applications.domain.model.entity.ApplicationApplicantProfile;

public interface ApplicationApplicantProfileRepository extends JpaRepository<ApplicationApplicantProfile,Long>{
    
}
