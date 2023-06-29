package com.teacherfinder.applications.domain.model.entity;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.teacherfinder.applications.domain.model.aggregate.Application;
import com.teacherfinder.applications.domain.model.valueObjects.AcademicInformation;
import com.teacherfinder.applications.domain.model.valueObjects.ContactInformation;
import com.teacherfinder.applications.domain.model.valueObjects.PersonalInformation;

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
@Table(name = "application_applicant_profile")
public class ApplicationApplicantProfile {
    
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
    private List<ApplicationJobExperienceInformation> jobExperienceInformations;

    @OneToOne(mappedBy = "applicantProfile")
    private Application application;
}
