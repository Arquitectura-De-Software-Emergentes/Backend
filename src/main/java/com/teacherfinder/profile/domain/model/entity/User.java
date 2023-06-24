package com.teacherfinder.profile.domain.model.entity;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

import com.teacherfinder.profile.domain.model.Enum.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class User {

    @NotBlank(message = "username must not be empty")
    private String username;

    @NotBlank(message = "password must not be empty")
    private String password;

    private Role role;
}
