package com.teacherfinder.profile.domain.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.teacherfinder.profile.domain.model.aggregate.ApplicantProfile;
import com.teacherfinder.profile.domain.model.valueObjects.JobExperienceInformationId;

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
@Table(name = "job_experience_information")
public class JobExperienceInformation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private JobExperienceInformationId jobExperienceInformationId;

    private String company;
    private String position;
    private String time;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "applicant_profile_id", nullable = true)
    private ApplicantProfile applicantProfile;
}
