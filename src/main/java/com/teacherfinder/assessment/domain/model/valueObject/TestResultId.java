package com.teacherfinder.assessment.domain.model.valueObject;

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
public class TestResultId implements Serializable{
    private Long applicantId;
    private Long assessmentId;
}
