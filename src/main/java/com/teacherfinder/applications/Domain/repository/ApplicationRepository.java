package com.teacherfinder.applications.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teacherfinder.applications.domain.model.aggregate.Application;
import com.teacherfinder.applications.domain.model.valueObjects.ApplicationId;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, ApplicationId>{
    
}