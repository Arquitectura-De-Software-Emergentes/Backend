package com.teacherfinder.profile.domain.service;

import com.teacherfinder.profile.domain.model.aggregate.User;

public interface AuthService {
    //Auth
    User register(User user);
    
}
