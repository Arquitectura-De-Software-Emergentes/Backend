package com.teacherfinder.offers.application.dto;

import com.teacherfinder.offers.domain.model.Enum.Availability;
import com.teacherfinder.offers.domain.model.valueObjects.Money;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.Date;

@Getter
@Setter
public class CreateJobOfferResource {
    private Long id;
    private Long recruiterId;
    private String title;
    private String description;
    private Date initialDate;
    private Date endDate;

    @Embedded
    private Money salary;

    private Long maxApplications;

    @Enumerated(value = EnumType.STRING)
    private Availability availability;
}