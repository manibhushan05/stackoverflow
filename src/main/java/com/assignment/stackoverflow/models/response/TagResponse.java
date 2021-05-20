package com.assignment.stackoverflow.models.response;

import com.assignment.stackoverflow.models.Question;
import com.assignment.stackoverflow.models.Tag;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class TagResponse {
    private Tag tag;
    private Page<Question> questions;
}
