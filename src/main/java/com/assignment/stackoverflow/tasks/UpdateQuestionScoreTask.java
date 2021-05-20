package com.assignment.stackoverflow.tasks;

import com.assignment.stackoverflow.models.Question;
import com.assignment.stackoverflow.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class UpdateQuestionScoreTask {
    @Autowired
    QuestionRepository questionRepository;

    @Async
    public void updateQuestionScore(Question question) {
        long score = (long) (question.getDownVoteCount() + question.getUpVoteCount() * 0.2 + question.getAnswers().size() * 5L);
        question.setScore(score);
        questionRepository.save(question);
    }
}
