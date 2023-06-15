package com.teacherfinder.profile.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.teacherfinder.profile.domain.model.entity.ApplicantProfile;

public interface ApplicantProfileRepository extends JpaRepository<ApplicantProfile, Long>{
}
