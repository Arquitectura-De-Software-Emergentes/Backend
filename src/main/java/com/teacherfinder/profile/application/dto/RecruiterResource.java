package com.teacherfinder.profile.application.dto;

import com.teacherfinder.profile.domain.model.Enum.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecruiterResource {
    private Long userId;
    private String username;
    private String position;
    private Role role;
}
