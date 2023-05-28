package com.teacherfinder.offers.domain.model.aggregate;


import com.teacherfinder.offers.domain.model.Enum.Modality;
import com.teacherfinder.offers.domain.model.Enum.Type;
import com.teacherfinder.offers.domain.model.valueObject.Course;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "positions_profiles")
@Inheritance(strategy = InheritanceType.JOINED)
public class PositionProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Course course;

    @Enumerated(value = EnumType.STRING)
    private Modality experience;

    @Enumerated(value = EnumType.STRING)
    private Type type;
}
