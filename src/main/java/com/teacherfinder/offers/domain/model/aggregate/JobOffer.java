package com.teacherfinder.offers.domain.model.aggregate;

import com.teacherfinder.offers.domain.model.Enum.Availability;
import com.teacherfinder.offers.domain.model.valueObject.Money;
import com.teacherfinder.offers.domain.model.valueObject.RecruiterId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "job_offers")
@Inheritance(strategy = InheritanceType.JOINED)
public class JobOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    RecruiterId recruiterId;
    private String title;
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
            referencedColumnName = "id",
             nullable = false)
    private PositionProfile positionProfile;

}
