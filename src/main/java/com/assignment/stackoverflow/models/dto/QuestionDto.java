package com.assignment.stackoverflow.models.dto;

import com.assignment.stackoverflow.enums.QuestionStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class QuestionDto {

    private Long id;

    private String title;

    private String description;

    private String content;

    private Long score;

    private Long viewCount;

    private Long downVoteCount;

    private Long upVoteCount;

    private QuestionStatus status;

    private List<AnswerDto> answers;

    private List<TagDto> linkedTags;

    private List<String> linkedTagName;
}
