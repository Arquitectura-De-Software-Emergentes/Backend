package com.teacherfinder.offers.domain.model.entity;


import com.teacherfinder.offers.domain.model.Enum.Experience;
import com.teacherfinder.offers.domain.model.Enum.Modality;
import com.teacherfinder.offers.domain.model.Enum.Type;
import com.teacherfinder.offers.domain.model.valueObjects.Course;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@With
@Getter
@Setter
@Entity
@Table(name = "positions_profiles")
@Inheritance(strategy = InheritanceType.JOINED)
public class PositionProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    private Course course;

    @Enumerated(value = EnumType.STRING)
    private Modality modality;

    @Enumerated(value= EnumType.STRING)
    private Experience experience;

    @Enumerated(value = EnumType.STRING)
    private Type type;
}
