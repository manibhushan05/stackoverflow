package com.assignment.stackoverflow.models.dto;

import com.assignment.stackoverflow.models.Answer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AnswerDto {

    private String answerText;

    private boolean accepted;

    private Long upVoteCount;

    private Long downVoteCount;

    private Long questionId;

    private Long parentAnswerId;

    private List<AnswerDto> linkedAnswers;
}
