package com.assignment.stackoverflow.models;

import com.assignment.stackoverflow.utils.Auditable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Table(name = "answers", indexes = {
        @Index(name = "answer_answer_text_index", columnList = "answer_text"),
        @Index(name = "answer_question_id_index", columnList = "question_id"),
})
@Getter
@Setter
public class Answer extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String answerText;

    private boolean accepted = false;

    @Column(nullable = false)
    private Long upVoteCount = 0L;

    @Column(nullable = false)
    private Long downVoteCount = 0L;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "question_id")
    @NotNull
    private Question question;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_answer_id")
    private Answer parentAnswer;

    @OneToMany(mappedBy = "parentAnswer")
    private Set<Answer> linkedAnswers = new HashSet<>();
}
