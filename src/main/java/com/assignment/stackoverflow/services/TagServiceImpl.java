package com.assignment.stackoverflow.services;

import com.assignment.stackoverflow.exceptions.RecordNotFoundException;
import com.assignment.stackoverflow.models.Tag;
import com.assignment.stackoverflow.models.dto.TagDto;
import com.assignment.stackoverflow.models.request.TagRequestPayload;
import com.assignment.stackoverflow.repositories.TagRepository;
import com.assignment.stackoverflow.utils.BeanMapper;
import com.assignment.stackoverflow.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagRepository tagRepository;

    public Page<TagDto> index(TagRequestPayload tagRequestPayload, Pageable pageable) {
        return tagRepository.findAll(pageable).map(this::convertTagToDto);
    }


    public TagDto update(Long id, TagDto tagDto) {
        Optional<Tag> tag=tagRepository.findById(id);
        if (tag.isEmpty()){
            throw new RecordNotFoundException(String.format("Tag not found with id = %s",id));
        }
        CommonUtil.copyNonNullProperties(tagDto, tag.get());
        tagRepository.save(tag.get());
        return convertTagToDto(tag.get());
    }

    public TagDto create(TagDto tagDto) {
        Tag tag = new Tag();
        CommonUtil.copyNonNullProperties(tagDto, tag);
        tagRepository.save(tag);
        return convertTagToDto(tag);
    }

    private TagDto convertTagToDto(Tag tag) {
        BeanMapper mapper = new BeanMapper();
        return mapper.map(tag, TagDto.class);
    }
}
