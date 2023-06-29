package com.teacherfinder.profile.application.dto;

import javax.validation.constraints.NotBlank;

import com.teacherfinder.profile.domain.model.Enum.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRecruiterResource {
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    private String position;

    private Role role;
}
