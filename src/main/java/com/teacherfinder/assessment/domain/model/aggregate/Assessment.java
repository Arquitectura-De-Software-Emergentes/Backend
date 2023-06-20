package com.teacherfinder.assessment.domain.model.aggregate;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.teacherfinder.assessment.domain.model.entity.TestActivity;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assessmentId;
    private Long jobOfferId;
    private Date initialAvailableDate;
    private Date endAvailableDate;
    private Boolean enable = false;
    @OneToOne(mappedBy = "assessment")
    private TestActivity test;
}
