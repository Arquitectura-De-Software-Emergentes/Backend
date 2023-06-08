package com.teacherfinder.offers.application.dto;

import com.google.type.Money;
import com.teacherfinder.offers.domain.model.Enum.Availability;
import com.teacherfinder.offers.domain.model.entity.PositionProfile;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UpdateJobOfferResource {
    private Long recruiterId;
    private String title;
    private String description;
    private Date initialDate;
    private Date endDate;
    private Money salary;
    private Long maxApplications;
    private Availability availability;
    private PositionProfile positionProfile;
}