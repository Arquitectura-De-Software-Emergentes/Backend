package com.teacherfinder.security.domain.model.valueObjects;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class AcademicInformation {
    private String school;
    private String specialty;
    private String reference;
}
