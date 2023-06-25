package com.teacherfinder.profile.application.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.teacherfinder.profile.application.dto.UserResource;
import com.teacherfinder.profile.domain.model.aggregate.User;
import com.teacherfinder.shared.mapping.EnhancedModelMapper;

public class UserMapper {
    @Autowired
    EnhancedModelMapper mapper;

    public UserResource toResource(User user){
        return mapper.map(user, UserResource.class);
    }
}
