package com.teacherfinder.Profile.dtos;

import javax.validation.constraints.NotBlank;

import com.teacherfinder.Profile.domain.model.valueObjects.CurriculumVitae;
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
}
