package com.teacherfinder.profile.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teacherfinder.profile.domain.model.aggregate.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
