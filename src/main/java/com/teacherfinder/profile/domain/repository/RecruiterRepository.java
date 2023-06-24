package com.teacherfinder.profile.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teacherfinder.profile.domain.model.aggregate.Recruiter;

public interface RecruiterRepository extends JpaRepository<Recruiter, Long>{
    
}
