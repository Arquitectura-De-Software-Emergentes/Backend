package com.teacherfinder.assessment.domain.model.entity;


import com.teacherfinder.assessment.domain.model.aggregate.Assessment;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "video_presentations")
public class VideoPresentation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

    @NotNull
    @NotBlank
    String feedback;

    @OneToOne
    @JoinColumn(name = "assessment_id")
    private Assessment assessment;
}
