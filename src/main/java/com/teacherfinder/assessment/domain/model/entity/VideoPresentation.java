package com.teacherfinder.assessment.domain.model.entity;


import com.teacherfinder.assessment.domain.model.aggregate.Assessment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
@Entity
@Table(name = "video_presentations")
public class VideoPresentation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

    @NotNull(message = "applicant id is required")
    private Long applicantId;

    @NotNull
    @NotBlank
    String feedback;

    @OneToOne
    @JoinColumn(name = "assessment_id")
    private Assessment assessment;
}
