package com.teacherfinder.profile.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.teacherfinder.profile.domain.model.aggregate.Applicant;

public interface ApplicantRepository extends JpaRepository<Applicant,Long>{
    
}
