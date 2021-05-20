package com.assignment.stackoverflow.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collections;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
public class CustomException extends Exception {

    private final String error;
    private final List<String> errors;

    public CustomException(String error) {
        this.error = error;
        this.errors = Collections.singletonList(error);
    }
}
