package com.teacherfinder.applications.domain.model.aggregate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.teacherfinder.applications.domain.model.entity.ApplicationApplicantProfile;
import com.teacherfinder.applications.domain.model.valueObjects.ApplicationId;

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
    
    @EmbeddedId
    ApplicationId applicationId;

    @NotBlank
    private String status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_profile_id", referencedColumnName = "applicantProfileId")
    private ApplicationApplicantProfile applicantProfile;
}
