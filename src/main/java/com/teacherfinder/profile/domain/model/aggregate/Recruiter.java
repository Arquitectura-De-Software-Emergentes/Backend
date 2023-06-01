package com.teacherfinder.profile.domain.model.aggregate;
import com.teacherfinder.profile.domain.model.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "recruiters")
public class Recruiter extends User {
    @Id
    private Long id;
    @OneToOne
    @JoinColumn(name = "institution_profile_id")
    private InstitutionProfile institutionProfile;
    private String position;
}