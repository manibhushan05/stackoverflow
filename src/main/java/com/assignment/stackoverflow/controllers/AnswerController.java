package com.assignment.stackoverflow.controllers;

import com.assignment.stackoverflow.models.dto.AnswerDto;
import com.assignment.stackoverflow.models.dto.QuestionDto;
import com.assignment.stackoverflow.models.request.AnswerRequestPayload;
import com.assignment.stackoverflow.services.AnswerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("api/v1/answers")
@Slf4j
public class AnswerController extends BaseController {
    @Autowired
    AnswerService answerService;

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<Map<String, Object>> index(AnswerRequestPayload answerRequestPayload, HttpServletRequest request) {
        Pageable pageable = getPaginationAndSortingParams(request);
        Page<AnswerDto> answerDtoList = answerService.findAnswerByParentAnswerId(answerRequestPayload, pageable);
        Map<String, Object> responseMapper = getPaginatedContent(answerDtoList);
        return ResponseEntity.ok(responseMapper);
    }

    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<AnswerDto> create(@RequestBody AnswerDto answerDto) {
        return new ResponseEntity<>(answerService.create(answerDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{answerId}", produces = "application/json")
    public ResponseEntity<AnswerDto> update(@PathVariable Long answerId, @RequestBody AnswerDto answerDto) {
        return new ResponseEntity<>(answerService.update(answerId, answerDto), HttpStatus.OK);
    }
    @PutMapping(value = "/{answerId}/vote", produces = "application/json")
    public ResponseEntity<AnswerDto> vote(@PathVariable Long answerId, @RequestBody String voteType){
        return new ResponseEntity<>(answerService.vote(answerId, voteType), HttpStatus.OK);
    }
}
