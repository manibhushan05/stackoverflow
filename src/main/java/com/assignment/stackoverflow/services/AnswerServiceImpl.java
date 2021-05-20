package com.assignment.stackoverflow.services;

import com.assignment.stackoverflow.exceptions.BadRequestException;
import com.assignment.stackoverflow.exceptions.RecordNotFoundException;
import com.assignment.stackoverflow.models.Answer;
import com.assignment.stackoverflow.models.Question;
import com.assignment.stackoverflow.models.User;
import com.assignment.stackoverflow.models.dto.AnswerDto;
import com.assignment.stackoverflow.models.dto.QuestionDto;
import com.assignment.stackoverflow.models.request.AnswerRequestPayload;
import com.assignment.stackoverflow.repositories.AnswerRepository;
import com.assignment.stackoverflow.repositories.QuestionRepository;
import com.assignment.stackoverflow.utils.BeanMapper;
import com.assignment.stackoverflow.utils.CommonUtil;
import com.assignment.stackoverflow.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    QuestionRepository questionRepository;

    public Page<AnswerDto> findAnswerByParentAnswerId(AnswerRequestPayload answerRequestPayload, Pageable pageable) {
        return answerRepository.findAllByParentAnswerId(answerRequestPayload.getId(), pageable).map(this::convertAnswerToDto);
    }

    public AnswerDto update(Long id, AnswerDto answerDto) {
        Optional<Answer> answer = answerRepository.findById(id);
        if (answer.isEmpty()) {
            throw new RecordNotFoundException(String.format("Answer not found with id = %s", id));
        }
        CommonUtil.copyNonNullProperties(answerDto, answer.get());
        answerRepository.save(answer.get());
        return convertAnswerToDto(answer.get());
    }

    public AnswerDto create(AnswerDto answerDto) {
        if (answerDto.getQuestionId() == null) {
            throw new BadRequestException("Question is null");
        }
        Optional<Question> question = questionRepository.findById(answerDto.getQuestionId());
        if (question.isEmpty()) {
            throw new BadRequestException("Question is null");
        }
        Answer answer = new Answer();
        CommonUtil.copyNonNullProperties(answerDto, answer);
        if (answerDto.getParentAnswerId() != null) {
            Optional<Answer> parentAnswer = answerRepository.findById(answerDto.getParentAnswerId());
            parentAnswer.ifPresent(answer::setParentAnswer);
        }
        answer.setQuestion(question.get());
        answerRepository.save(answer);
        return convertAnswerToDto(answer);
    }


    public AnswerDto vote(Long id, String voteType) {
        Optional<Answer> answer = answerRepository.findById(id);
        if (answer.isEmpty()) {
            throw new RecordNotFoundException(String.format("Answer not found with id = %s", id));
        }
        if (voteType.equalsIgnoreCase(Constants.UP_VOTE)) {
            answer.get().setUpVoteCount(answer.get().getUpVoteCount() + 1);
        } else if (voteType.equalsIgnoreCase(Constants.DOWN_VOTE)) {
            answer.get().setDownVoteCount(answer.get().getDownVoteCount() + 1);
        }
        answerRepository.save(answer.get());
        return convertAnswerToDto(answer.get());
    }

    private AnswerDto convertAnswerToDto(Answer answer) {
        BeanMapper mapper = new BeanMapper();
        return mapper.map(answer, AnswerDto.class);
    }
}