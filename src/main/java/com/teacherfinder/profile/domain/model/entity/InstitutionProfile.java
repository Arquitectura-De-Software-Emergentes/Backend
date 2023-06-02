package com.teacherfinder.profile.domain.model.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "institutions_profiles")
public class InstitutionProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String urlWebPage;
    private String phoneNumber;
    @Transient
    private Byte[] image;
    private String address;
}