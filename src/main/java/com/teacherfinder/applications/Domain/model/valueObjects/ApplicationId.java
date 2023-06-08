package com.teacherfinder.applications.domain.model.valueObjects;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

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
    @NotNull(message = "applicant id is required")
    private Long applicantId;
    @NotNull(message = "job offer id is required")
    private Long jobOfferId;
}
