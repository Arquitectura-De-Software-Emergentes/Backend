package com.teacherfinder.applications.domain.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
@Entity
@Table(name = "application_job_experience_information")
public class ApplicationJobExperienceInformation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobExperienceInformationId;

    private String company;
    private String position;
    private String time;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "applicant_profile_id", nullable = true)
    private ApplicationApplicantProfile applicantProfile;
}
