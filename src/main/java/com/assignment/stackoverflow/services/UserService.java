package com.assignment.stackoverflow.services;

import com.assignment.stackoverflow.models.dto.UserDto;
import com.assignment.stackoverflow.models.request.UserRequestPayload;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserDto> index(UserRequestPayload userRequestPayload, Pageable pageable);

    UserDto update(Long id, UserDto userDto);

    UserDto create(UserDto userDto);
}
