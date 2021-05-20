package com.assignment.stackoverflow.services;

import com.assignment.stackoverflow.models.dto.QuestionDto;
import com.assignment.stackoverflow.models.request.QuestionRequestPayload;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionService {
    Page<QuestionDto> index(QuestionRequestPayload questionRequestPayload, Pageable pageable);

    QuestionDto update(Long id, QuestionDto questionDto);
    QuestionDto vote(Long id, String voteType);

    QuestionDto create(QuestionDto questionDto);
}
