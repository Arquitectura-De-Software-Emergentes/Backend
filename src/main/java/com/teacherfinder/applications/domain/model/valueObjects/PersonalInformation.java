package com.teacherfinder.applications.domain.model.valueObjects;

import java.util.Date;

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
public class PersonalInformation {
    private String name;
    private String lastname;
    private Date birthDate;
    private String address;
}
