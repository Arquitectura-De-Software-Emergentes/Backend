package com.teacherfinder.profile.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teacherfinder.profile.domain.model.aggregate.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findOneByUsername(String username);
}
