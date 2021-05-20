package com.assignment.stackoverflow.services;

import com.assignment.stackoverflow.models.Tag;
import com.assignment.stackoverflow.models.dto.TagDto;
import com.assignment.stackoverflow.models.request.TagRequestPayload;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Map;


public interface TagService {
    Page<TagDto> index(TagRequestPayload tagRequestPayload, Pageable pageable);

    TagDto update(Long id, TagDto tagDto);

    TagDto create(TagDto tagDto);
}
