package com.assignment.stackoverflow.repositories;
import com.assignment.stackoverflow.models.Tag;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends PagingAndSortingRepository<Tag, Long>, JpaSpecificationExecutor<Tag> {
    List<Tag> findAllByNameIgnoreCaseIn(List<String> names);
}
