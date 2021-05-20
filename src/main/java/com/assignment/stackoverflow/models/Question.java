package com.assignment.stackoverflow.models;

import com.assignment.stackoverflow.enums.QuestionClosingRemark;
import com.assignment.stackoverflow.enums.QuestionStatus;
import com.assignment.stackoverflow.utils.Auditable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Table(name = "questions", indexes = {
        @Index(name = "question_title_index", columnList = "title"),
        @Index(name = "question_content_index", columnList = "content"),
        @Index(name = "question_status_index", columnList = "status")
})
@Getter
@Setter
public class Question extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String title;

    @NotNull
    @Size(max = 250)
    private String description;

    @NotNull
    private String content;

    @NotNull
    private Long score = 0L;

    @Column(name = "view_count", nullable = false)
    private Long viewCount = 0L;

    @Column(name = "up_vote_count", nullable = false)
    private Long upVoteCount = 0L;

    @Column(name = "down_vote_count", nullable = false)
    private Long downVoteCount = 0L;

    @Enumerated(EnumType.STRING)
    @NotNull
    private QuestionStatus status = QuestionStatus.OPEN;

    @Enumerated(EnumType.STRING)
    private QuestionClosingRemark closingRemark;

    @JsonManagedReference
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "question_tags",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> linkedTags;
}
