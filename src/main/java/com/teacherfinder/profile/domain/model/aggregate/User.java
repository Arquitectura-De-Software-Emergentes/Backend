package com.teacherfinder.profile.domain.model.aggregate;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.teacherfinder.profile.domain.model.Enum.Role;
import com.teacherfinder.profile.domain.model.entity.ApplicantProfile;
import com.teacherfinder.profile.domain.model.entity.InstitutionProfile;
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
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = "username must not be empty")
    private String username;

    @NotBlank(message = "password must not be empty")
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Embedded
    private CurriculumVitae cv;

    @OneToOne(mappedBy = "applicant")
    private ApplicantProfile applicantProfile;

    @OneToOne(mappedBy = "recruiter")
    private InstitutionProfile institutionProfile;
    
    private String position;
}
