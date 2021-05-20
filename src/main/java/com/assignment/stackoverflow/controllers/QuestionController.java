package com.assignment.stackoverflow.controllers;

import com.assignment.stackoverflow.models.dto.QuestionDto;
import com.assignment.stackoverflow.models.request.QuestionRequestPayload;
import com.assignment.stackoverflow.services.QuestionService;
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
@RequestMapping("api/v1/questions")
@Slf4j
public class QuestionController extends BaseController {
    @Autowired
    QuestionService questionService;

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<Map<String, Object>> index(QuestionRequestPayload questionRequestPayload, HttpServletRequest request) {
        Pageable pageable = getPaginationAndSortingParams(request);
        Page<QuestionDto> questionDtos = questionService.index(questionRequestPayload, pageable);
        Map<String, Object> responseMapper = getPaginatedContent(questionDtos);
        return ResponseEntity.ok(responseMapper);
    }

    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<QuestionDto> create(@RequestBody QuestionDto questionDto) {
        return new ResponseEntity<>(questionService.create(questionDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{questionId}", produces = "application/json")
    public ResponseEntity<QuestionDto> update(@PathVariable Long questionId, @RequestBody QuestionDto questionDto) {
        return new ResponseEntity<>(questionService.update(questionId, questionDto), HttpStatus.OK);
    }

    @PutMapping(value = "/{questionId}/vote", produces = "application/json")
    public ResponseEntity<QuestionDto> vote(@PathVariable Long questionId,@RequestBody String voteType){
        return new ResponseEntity<>(questionService.vote(questionId, voteType), HttpStatus.OK);
    }
}
