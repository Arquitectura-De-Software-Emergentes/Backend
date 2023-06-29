package com.teacherfinder.profile.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    private UserResource user;
    private String token;
}
