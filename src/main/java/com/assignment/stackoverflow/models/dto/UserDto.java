package com.assignment.stackoverflow.models.dto;

import com.assignment.stackoverflow.enums.UserStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String username;
    private UserStatus status;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private int reputation;
}
