package com.teacherfinder.profile.application.dto;

import com.teacherfinder.profile.domain.model.Enum.Role;
import com.teacherfinder.profile.domain.model.valueObjects.CurriculumVitae;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicantResource {
    private Long applicantId;
    private String username;
    private CurriculumVitae cv;
    private Role role;

}
