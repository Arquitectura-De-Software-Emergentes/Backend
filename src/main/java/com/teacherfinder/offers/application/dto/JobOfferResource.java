package com.teacherfinder.offers.application.dto;

import com.teacherfinder.offers.domain.model.Enum.Availability;
import com.teacherfinder.offers.domain.model.entity.PositionProfile;
import com.teacherfinder.offers.domain.model.valueObjects.Money;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class JobOfferResource {
    private Long id;
    private Long recruiterId;
    private String title;
    private String description;
    private Date initialDate;
    private Date endDate;
    private Money salary;
    private Long maxApplications;
    private Long numberApplications;
    private Availability availability;
    private PositionProfile positionProfile;
}