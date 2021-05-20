package com.assignment.stackoverflow.controllers;

import com.assignment.stackoverflow.models.dto.TagDto;
import com.assignment.stackoverflow.models.request.TagRequestPayload;
import com.assignment.stackoverflow.services.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("api/v1/tags")
@Slf4j
public class TagController extends BaseController {
    @Autowired
    TagService tagService;

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<Map<String, Object>> index(TagRequestPayload tagRequestPayload, HttpServletRequest request) {
        Pageable pageable = getPaginationAndSortingParams(request);
        Page<TagDto> tagDtoPage = tagService.index(tagRequestPayload, pageable);
        Map<String, Object> responseMapper = getPaginatedContent(tagDtoPage);
        return ResponseEntity.ok(responseMapper);
    }

    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<TagDto> create(@RequestBody TagDto tagDto) {
        return new ResponseEntity<>(tagService.create(tagDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{tagId}", produces = "application/json")
    public ResponseEntity<TagDto> update(@PathVariable Long tagId, @RequestBody TagDto tagDto) {
        return new ResponseEntity<>(tagService.update(tagId, tagDto), HttpStatus.OK);
    }
}
