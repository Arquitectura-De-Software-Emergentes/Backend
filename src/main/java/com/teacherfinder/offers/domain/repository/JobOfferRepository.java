package com.teacherfinder.offers.domain.repository;

import com.teacherfinder.offers.domain.model.aggregate.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobOfferRepository extends JpaRepository<JobOffer,Long> {
    List<JobOffer> findByRecruiterId(Long recruiterId);
}
