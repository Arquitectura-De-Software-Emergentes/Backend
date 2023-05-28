package com.teacherfinder.Security.Domain.Model.aggregate;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.teacherfinder.Security.Domain.Model.ValueObjects.AcademicInformation;
import com.teacherfinder.Security.Domain.Model.ValueObjects.ContactInformation;
import com.teacherfinder.Security.Domain.Model.ValueObjects.PersonalInformation;

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

    @OneToOne(mappedBy = "applicantProfile")
    private Applicant applicant;

}
