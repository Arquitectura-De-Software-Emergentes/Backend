package com.teacherfinder.Profile.dtos;

import com.teacherfinder.Profile.domain.model.valueObjects.CurriculumVitae;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicantResource {
    private Long applicantId;
    private String username;
    private CurriculumVitae cv;
}
