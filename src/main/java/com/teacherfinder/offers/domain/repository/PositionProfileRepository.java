package com.teacherfinder.offers.domain.repository;

import com.teacherfinder.offers.domain.model.entity.PositionProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionProfileRepository extends JpaRepository<PositionProfile, Long> {
    PositionProfile findByName(String name);
}
