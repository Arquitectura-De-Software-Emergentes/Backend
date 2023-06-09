package com.teacherfinder.profile.domain.model.entity;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.teacherfinder.profile.domain.model.aggregate.User;
import com.teacherfinder.profile.domain.model.valueObjects.AcademicInformation;
import com.teacherfinder.profile.domain.model.valueObjects.ContactInformation;
import com.teacherfinder.profile.domain.model.valueObjects.PersonalInformation;

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
@Table(name = "applicant_profile")
public class ApplicantProfile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicantProfileId;

    @Embedded
    private AcademicInformation academicInformation;

    @Embedded
    private ContactInformation contactInformation;

    @Embedded
    private PersonalInformation personalInformation;

    @OneToMany(mappedBy = "applicantProfile")
    private List<JobExperienceInformation> jobExperienceInformations;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_id")
    private User applicant;

}
