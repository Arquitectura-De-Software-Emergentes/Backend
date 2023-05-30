package com.teacherfinder.security.domain.model.valueObjects;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class JobExperienceInformationId implements Serializable{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobExperienceInformationId;
}
