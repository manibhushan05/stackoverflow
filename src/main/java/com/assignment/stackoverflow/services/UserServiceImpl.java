package com.assignment.stackoverflow.services;

import com.assignment.stackoverflow.exceptions.RecordNotFoundException;
import com.assignment.stackoverflow.models.Question;
import com.assignment.stackoverflow.models.User;
import com.assignment.stackoverflow.models.dto.UserDto;
import com.assignment.stackoverflow.models.request.UserRequestPayload;
import com.assignment.stackoverflow.repositories.UserRepository;
import com.assignment.stackoverflow.utils.BeanMapper;
import com.assignment.stackoverflow.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    public Page<UserDto> index(UserRequestPayload userRequestPayload, Pageable pageable) {
        return userRepository.findAll(pageable).map(this::convertUserToDto);
    }

    public UserDto update(Long id, UserDto userDto) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new RecordNotFoundException(String.format("User not found with id = %s", id));
        }
        CommonUtil.copyNonNullProperties(userDto, user.get());
        userRepository.save(user.get());
        return convertUserToDto(user.get());
    }

    public UserDto create(UserDto userDto) {
        User user = new User();
        CommonUtil.copyNonNullProperties(userDto, user);
        userRepository.save(user);
        return convertUserToDto(user);
    }

    private UserDto convertUserToDto(User user) {
        BeanMapper mapper = new BeanMapper();
        return mapper.map(user, UserDto.class);
    }
}
