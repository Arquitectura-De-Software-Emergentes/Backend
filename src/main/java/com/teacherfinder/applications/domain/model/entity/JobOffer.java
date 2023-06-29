package com.teacherfinder.applications.domain.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobOffer {
    private Long jobOfferId;
    private String title;
    private String description;
}
