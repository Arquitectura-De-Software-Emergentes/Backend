package com.teacherfinder.assessment.application.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateQuestion {

    @NotBlank(message = "The statement is required")
    private String statement;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CreateQuestionOptionResource> options;

    @ManyToOne(fetch = FetchType.LAZY)
    private Long testId;
}
