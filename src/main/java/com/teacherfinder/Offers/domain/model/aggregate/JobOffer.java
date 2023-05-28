package com.teacherfinder.Offers.domain.model.aggregate;

import com.teacherfinder.Offers.domain.model.Enum.Availability;
import com.teacherfinder.Offers.domain.model.valueObject.Money;
import com.teacherfinder.Offers.domain.model.valueObject.RecruiterId;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.lang.model.util.AbstractAnnotationValueVisitor6;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "job_offer")
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
