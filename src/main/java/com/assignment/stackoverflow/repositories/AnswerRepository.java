package com.assignment.stackoverflow.repositories;

import com.assignment.stackoverflow.models.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends PagingAndSortingRepository<Answer, Long>, JpaSpecificationExecutor<Answer> {
    Page<Answer> findAllByQuestionId(Long questionId,Pageable pageable);
    Page<Answer> findAllByParentAnswerId(Long parentAnswerId,Pageable pageable);
}
