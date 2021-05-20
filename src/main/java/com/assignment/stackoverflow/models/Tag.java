package com.assignment.stackoverflow.models;

import com.assignment.stackoverflow.utils.Auditable;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Table(
        name = "tags",
        indexes = {
                @Index(name = "tag_name_index", columnList = "name")
        }
)
@Getter
@Setter
public class Tag extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }, mappedBy = "linkedTags")
    private Set<Question> questions;
}
