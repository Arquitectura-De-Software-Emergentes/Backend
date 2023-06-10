package com.teacherfinder.offers.domain.repository;

import com.teacherfinder.offers.domain.model.aggregate.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer,Long> {
    List<JobOffer> findByRecruiterId(Long recruiterId);
}
