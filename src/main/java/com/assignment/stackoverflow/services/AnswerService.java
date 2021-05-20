package com.assignment.stackoverflow.services;

import com.assignment.stackoverflow.models.dto.AnswerDto;
import com.assignment.stackoverflow.models.request.AnswerRequestPayload;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AnswerService {
    Page<AnswerDto> findAnswerByParentAnswerId(AnswerRequestPayload answerRequestPayload, Pageable pageable);

    AnswerDto update(Long id, AnswerDto answerDto);

    AnswerDto create(AnswerDto answerDto);

    AnswerDto vote(Long id, String voteType);
}
