package com.assignment.stackoverflow.repositories;

import com.assignment.stackoverflow.models.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuestionRepository extends PagingAndSortingRepository<Question, Long>, JpaSpecificationExecutor<Question> {
    Page<Question> findAllByOrderByScoreDesc(Pageable pageable);
}
