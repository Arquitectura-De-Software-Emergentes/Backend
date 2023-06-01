package com.teacherfinder.offers.domain.model.repository;

import com.teacherfinder.offers.domain.model.aggregate.PositionProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionProfileRepository extends JpaRepository<PositionProfile, Long> {
}
