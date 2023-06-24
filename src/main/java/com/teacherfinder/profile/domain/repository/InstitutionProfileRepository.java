package com.teacherfinder.profile.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teacherfinder.profile.domain.model.entity.InstitutionProfile;

public interface InstitutionProfileRepository extends JpaRepository<InstitutionProfile,Long>{
    
}
