package com.teacherfinder.offers.dto;

import com.teacherfinder.offers.domain.model.Enum.Availability;
import com.teacherfinder.offers.domain.model.aggregate.PositionProfile;
import com.teacherfinder.offers.domain.model.valueObjects.Money;
import com.teacherfinder.offers.domain.model.valueObjects.RecruiterId;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class JobOfferResource {
    private Long id;
    private RecruiterId recruiterId;
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