package com.assignment.stackoverflow.services;

import com.assignment.stackoverflow.exceptions.RecordNotFoundException;
import com.assignment.stackoverflow.models.Question;
import com.assignment.stackoverflow.models.Tag;
import com.assignment.stackoverflow.models.dto.QuestionDto;
import com.assignment.stackoverflow.models.request.QuestionRequestPayload;
import com.assignment.stackoverflow.repositories.QuestionRepository;
import com.assignment.stackoverflow.repositories.TagRepository;
import com.assignment.stackoverflow.tasks.UpdateQuestionScoreTask;
import com.assignment.stackoverflow.utils.BeanMapper;
import com.assignment.stackoverflow.utils.CommonUtil;
import com.assignment.stackoverflow.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    TagRepository tagRepository;


    @Autowired
    UpdateQuestionScoreTask questionScoreTask;

    public Page<QuestionDto> index(QuestionRequestPayload questionRequestPayload, Pageable pageable) {
        return questionRepository.findAllByOrderByScoreDesc(pageable).map(this::convertQuestionToDto);
    }

    public QuestionDto update(Long id, QuestionDto questionDto) {
        Optional<Question> question = questionRepository.findById(id);
        if (question.isEmpty()) {
            throw new RecordNotFoundException(String.format("Question not found with id = %s", id));
        }
        CommonUtil.copyNonNullProperties(questionDto, question.get());
        List<Tag> tags = tagRepository.findAllByNameIgnoreCaseIn(questionDto.getLinkedTagName());
        question.get().setLinkedTags(tags);
        questionRepository.save(question.get());
        return convertQuestionToDto(question.get());
    }

    public QuestionDto vote(Long id, String voteType) {
        Optional<Question> question = questionRepository.findById(id);
        if (question.isEmpty()) {
            throw new RecordNotFoundException(String.format("Question not found with id = %s", id));
        }
        if (voteType.equalsIgnoreCase(Constants.UP_VOTE)) {
            question.get().setUpVoteCount(question.get().getUpVoteCount() + 1);
        } else if (voteType.equalsIgnoreCase(Constants.DOWN_VOTE)) {
            question.get().setDownVoteCount(question.get().getDownVoteCount() + 1);
        }
        questionRepository.save(question.get());
        questionScoreTask.updateQuestionScore(question.get());
        return convertQuestionToDto(question.get());
    }

    public QuestionDto create(QuestionDto questionDto) {
        Question question = new Question();
        CommonUtil.copyNonNullProperties(questionDto, question);
        List<Tag> tags = tagRepository.findAllByNameIgnoreCaseIn(questionDto.getLinkedTagName());
        question.setLinkedTags(tags);
        questionRepository.save(question);
        return convertQuestionToDto(question);
    }

    private QuestionDto convertQuestionToDto(Question question) {
        BeanMapper mapper = new BeanMapper();
        return mapper.map(question, QuestionDto.class);
    }
}
