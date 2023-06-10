package com.teacherfinder.offers.domain.model.aggregate;

import com.teacherfinder.offers.domain.model.Enum.Availability;
import com.teacherfinder.offers.domain.model.entity.PositionProfile;
import com.teacherfinder.offers.domain.model.valueObjects.Money;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@With
@Getter
@Setter
@Entity
@Table(name = "job_offers")
@Inheritance(strategy = InheritanceType.JOINED)
public class JobOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Recruiter id is required")
    private Long recruiterId;

    @NotNull
    @NotBlank
    private String title;
    @NotNull
    @NotBlank
    private String description;


    private Date initialDate;

    private Date endDate;

    @Embedded
    private Money salary;

    private Long maxApplications;
    private Long numberApplications;

    @Enumerated(value = EnumType.STRING)
    private Availability availability;

    @ManyToOne
    @JoinColumn(
            name = "id_positions_profiles" ,
            referencedColumnName = "id"
    )
    private PositionProfile positionProfile;

}
