package com.teacherfinder.assessment.domain.model.entity;

import java.time.LocalDate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.teacherfinder.assessment.domain.model.valueObject.TestResultId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "test_results")
public class TestResult {
    
    @EmbeddedId
    private TestResultId testResultId;

    private LocalDate submitDate;

    private Long score;

    private Boolean HasPassed;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "test_id", nullable = true)
    private TestActivity test;

}
