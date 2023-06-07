package com.teacherfinder.applications.domain.model.valueObjects;

import java.io.Serializable;

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
public class ApplicationId implements Serializable{
    private Long applicantId;
    private Long jobOfferId;
}
