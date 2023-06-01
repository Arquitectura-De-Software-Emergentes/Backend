package com.teacherfinder.profile.domain.model.aggregate;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.teacherfinder.profile.domain.model.entity.User;
import com.teacherfinder.profile.domain.model.valueObjects.CurriculumVitae;

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
public class Applicant extends User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicantId;

    @Embedded
    private CurriculumVitae cv;

    @OneToOne(mappedBy = "applicant")
    private ApplicantProfile applicantProfile;
}
