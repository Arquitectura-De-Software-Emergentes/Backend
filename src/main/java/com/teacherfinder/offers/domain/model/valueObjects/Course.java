package com.teacherfinder.offers.domain.model.valueObjects;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Embeddable
public class Course {
    private String course;
}
