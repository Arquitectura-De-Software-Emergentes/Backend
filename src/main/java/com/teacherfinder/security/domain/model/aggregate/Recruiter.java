package com.teacherfinder.security.domain.model.aggregate;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "recruiters")
public class Recruiter {
    @Id
    private Long id;
    @OneToOne
    @JoinColumn(name = "institution_profile_id")
    private InstitutionProfile institutionProfile;
    private String position;
}