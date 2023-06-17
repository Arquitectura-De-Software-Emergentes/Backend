package com.teacherfinder.assessment.domain.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

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
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The statement is required")
    private String statement;

    @ManyToOne(fetch = FetchType.LAZY)
    private TestActivity test;

    private String option1;

    private String option2;

    private String option3;

    private String option4;

    private String reponse;
}
