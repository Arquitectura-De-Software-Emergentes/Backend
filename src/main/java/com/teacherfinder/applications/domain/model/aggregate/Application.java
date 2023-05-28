package com.teacherfinder.applications.domain.model.aggregate;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.teacherfinder.applications.domain.model.valueObjects.ApplicantId;
import com.teacherfinder.applications.domain.model.valueObjects.JobOfferId;

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
@Table(name = "applications")
public class Application {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Embedded
    private ApplicantId applicantId;

    @Embedded
    private JobOfferId jobOfferId;

    @NotBlank
    private String status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_profile_id", referencedColumnName = "applicantProfileId")
    private ApplicationApplicantProfile applicantProfile;
}
