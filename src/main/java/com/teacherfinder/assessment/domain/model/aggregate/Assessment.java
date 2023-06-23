package com.teacherfinder.assessment.domain.model.aggregate;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.teacherfinder.assessment.domain.model.entity.TestActivity;

import com.teacherfinder.assessment.domain.model.entity.VideoPresentation;
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
@Table(name = "assessments")
public class Assessment {
    @Id
    private Long assessmentId;
    private Long jobOfferId;
    private Date initialAvailableDate;
    private Date endAvailableDate;

    @OneToOne
    @JoinColumn(name = "test_id")
    private TestActivity test;
     
    @OneToOne(mappedBy = "assessment")
    private VideoPresentation videoPresentation;
}
