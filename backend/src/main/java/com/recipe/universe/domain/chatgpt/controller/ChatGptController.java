package com.recipe.universe.domain.chatgpt.controller;

import com.recipe.universe.domain.chatgpt.dto.ChatGptResponseDto;
import com.recipe.universe.domain.chatgpt.dto.QuestionRequestDto;
import com.recipe.universe.domain.chatgpt.service.ChatGptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/chatGpt")
@RequiredArgsConstructor
public class ChatGptController {

    private final ChatGptService chatGptService;

    @PostMapping("/question")
    public ResponseEntity<ChatGptResponseDto> sendQuestion(@RequestBody QuestionRequestDto requestDto) {
        return new ResponseEntity<>(chatGptService.askQuestion(requestDto), HttpStatus.OK);
    }


}
