package com.swave.releasenotesharesystem.ChatGPT.controller;

import com.swave.releasenotesharesystem.ChatGPT.requestDTO.ChatGPTQuestionRequestDTO;
import com.swave.releasenotesharesystem.ChatGPT.requestDTO.ChatGPTResultDTO;
import com.swave.releasenotesharesystem.ChatGPT.service.ChatGPTService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gpt/")
public class ChatGptController {

    private final ChatGPTService chatGPTService;

    public ChatGptController(ChatGPTService chatGPTService) {
        this.chatGPTService = chatGPTService;
    }

    @PostMapping("/question")
    public ChatGPTResultDTO sendQuestion(@RequestBody ChatGPTQuestionRequestDTO chatGPTQuestionRequestDTO) {
        return chatGPTService.chatGptResult(chatGPTQuestionRequestDTO);
    }
}