package com.teacherfinder.security.domain.model.aggregate;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.teacherfinder.security.domain.model.valueObjects.CurriculumVitae;

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
@Table(name = "applicants")
public class Applicant {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicantId;

    @Embedded
    private CurriculumVitae cv;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_profile_id", referencedColumnName = "applicantProfileId")
    private ApplicantProfile applicantProfile;
}