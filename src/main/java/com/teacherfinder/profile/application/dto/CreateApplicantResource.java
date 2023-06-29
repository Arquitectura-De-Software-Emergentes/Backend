package com.teacherfinder.profile.application.dto;

import javax.validation.constraints.NotBlank;

import com.teacherfinder.profile.domain.model.Enum.Role;
import com.teacherfinder.profile.domain.model.valueObjects.CurriculumVitae;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateApplicantResource {
  
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    private CurriculumVitae cv;

    private Role role;

}
