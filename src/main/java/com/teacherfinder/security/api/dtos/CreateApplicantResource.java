package com.teacherfinder.security.api.dtos;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateApplicantResource {
  
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
