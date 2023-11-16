package com.avengers.commandSlash.controller;

import com.avengers.commandSlash.adaptor.ChatGptApiAdaptor;
import com.avengers.commandSlash.dto.ChatCompletionResponseDto.ChoiceDto;
import com.avengers.commandSlash.dto.ChatCompletionResponseDto.MessageDto;
import com.avengers.commandSlash.dto.CommandRequestDto;
import com.avengers.commandSlash.dto.CommandResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ChatGptController {

    private final ChatGptApiAdaptor chatGptApiAdaptor;
    @Value("${tockenId}")
    private String tokenId;
    @Value("${chatGptApiKey}")
    private String chatGptApiKey;

    @PostMapping("/chat")
    public ResponseEntity<CommandResponseDto> getChat(@RequestBody CommandRequestDto commandRequestDto)
        throws JsonProcessingException {

        String answer = communicateWithChatGpt(commandRequestDto.getText());

        return ResponseEntity.ok(new CommandResponseDto(answer));
    }

    private String communicateWithChatGpt(String text) throws JsonProcessingException {
        return chatGptApiAdaptor.getChatCompletion(text, chatGptApiKey).getChoices().get(0).getMessage().getContent();
    }
}
