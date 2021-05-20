package com.assignment.stackoverflow.models;

import com.assignment.stackoverflow.enums.UserStatus;
import com.assignment.stackoverflow.utils.Auditable;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Table(name = "users")
@Getter
@Setter
public class User extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private UserStatus status = UserStatus.ACTIVE;
    private String firstName;
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    private String phone;
    private int reputation;
}
