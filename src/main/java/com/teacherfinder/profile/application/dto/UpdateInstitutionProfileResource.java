package com.teacherfinder.profile.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateInstitutionProfileResource {
    private String name;
    private String description;
    private String urlWebPage;
    private String phoneNumber;
    private String image;
    private String address;
}
