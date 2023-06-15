package com.teacherfinder.applications.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.teacherfinder.applications.domain.model.aggregate.Application;
import com.teacherfinder.applications.domain.model.valueObjects.ApplicationId;

public interface ApplicationRepository extends JpaRepository<Application, ApplicationId>{
    List<Application> findByApplicationIdJobOfferId(long offerId);
}
