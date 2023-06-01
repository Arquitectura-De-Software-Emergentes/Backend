package com.teacherfinder.profile.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teacherfinder.profile.domain.model.aggregate.Applicant;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant,Long>{
    
}
