package com.teacherfinder.assessment.domain.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teacherfinder.assessment.domain.model.aggregate.Assessment;

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
@Table(name = "tests")
public class TestActivity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "The minmun score is required")
    private Long minimunScore;

    private Long numQuestions = 0L;

    @OneToMany(mappedBy = "test")
    @JsonIgnore
    private List<Question> questions = new ArrayList<Question>();

    @OneToOne
    @JoinColumn(name = "assessment_id")
    private Assessment assessment;

    public void addQuestion(Question question) {
        questions.add(question);
        numQuestions += 1;
    }

}
