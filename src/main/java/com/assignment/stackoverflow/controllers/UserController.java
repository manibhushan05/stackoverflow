package com.assignment.stackoverflow.controllers;

import com.assignment.stackoverflow.models.dto.UserDto;
import com.assignment.stackoverflow.models.request.UserRequestPayload;
import com.assignment.stackoverflow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("api/v1/users")
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<Map<String, Object>> index(UserRequestPayload userRequestPayload, HttpServletRequest request) {
        Pageable pageable = getPaginationAndSortingParams(request);
        Page<UserDto> userDtos = userService.index(userRequestPayload, pageable);
        Map<String, Object> responseMapper = getPaginatedContent(userDtos);
        return ResponseEntity.ok(responseMapper);
    }

    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.create(userDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{userId}", produces = "application/json")
    public ResponseEntity<UserDto> update(@PathVariable Long userId, @RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.update(userId, userDto), HttpStatus.OK);
    }
}
