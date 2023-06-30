package com.teacherfinder.assessment.domain.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    private Long recruiterId;

    @NotBlank(message = "The title must not be blank")
    @NotNull(message = "the title is required")
    private String title;

    @NotNull(message = "The minmun score is required")
    private Long minimunScore;

    private Long numQuestions = 0L;

    @OneToMany(mappedBy = "test")
    private List<Question> questions = new ArrayList<Question>();

    @OneToMany(mappedBy = "test")
    private List<Assessment> assessment = new ArrayList<Assessment>();

    public void addQuestion(Question question) {
        questions.add(question);
        numQuestions += 1;
    }

}
