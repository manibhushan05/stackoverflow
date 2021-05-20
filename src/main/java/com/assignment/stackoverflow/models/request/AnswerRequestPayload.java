package com.assignment.stackoverflow.models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerRequestPayload {
    private Long id;
    private Long questionId;
}
