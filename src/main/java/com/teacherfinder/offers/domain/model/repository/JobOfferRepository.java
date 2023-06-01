package com.teacherfinder.offers.domain.model.repository;

import com.teacherfinder.offers.domain.model.aggregate.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer,Long> {
}
