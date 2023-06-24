package com.teacherfinder.profile.domain.model.entity;

import javax.persistence.*;

import com.teacherfinder.profile.domain.model.aggregate.Recruiter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@With
@Table(name = "institutions_profiles")
public class InstitutionProfile {
    @Id
    private Long institutionProfileId;
    private String name;
    private String description;
    private String urlWebPage;
    private String phoneNumber;
    private String image;
    private String address;
    @OneToOne
    @JoinColumn(name = "recruiter_id")
    private Recruiter recruiter;
}