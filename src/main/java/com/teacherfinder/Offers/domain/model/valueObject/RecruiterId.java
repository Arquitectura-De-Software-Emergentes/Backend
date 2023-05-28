package com.teacherfinder.Offers.domain.model.valueObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecruiterId {

    private Long recruiterId;

}
