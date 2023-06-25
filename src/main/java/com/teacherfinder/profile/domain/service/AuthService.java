package com.teacherfinder.profile.domain.service;

import org.springframework.http.ResponseEntity;

import com.teacherfinder.profile.application.dto.AuthCredentials;
import com.teacherfinder.profile.domain.model.aggregate.User;

public interface AuthService {
    User register(User user);
    ResponseEntity<?>  login(AuthCredentials credentials);
    User getByusername(String username);   
    boolean exits(Long userId);
    
}
