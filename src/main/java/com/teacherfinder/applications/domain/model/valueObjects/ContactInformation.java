package com.teacherfinder.applications.domain.model.valueObjects;

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
public class ContactInformation {
    private String phone;
    private String email;
    private String mobilePhone;
}
