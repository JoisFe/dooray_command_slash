package com.avengers.commandSlash.adaptor;

import com.avengers.commandSlash.dto.ChatCompletionRequestDto;
import com.avengers.commandSlash.dto.ChatCompletionRequestDto.MessageDto;
import com.avengers.commandSlash.dto.ChatCompletionResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class ChatGptApiAdaptor {

    private static final String apiUrl = "https://api.openai.com/v1/chat/completions";
    private final RestTemplate restTemplate;

    public ChatCompletionResponseDto getChatCompletion(String question, String apiKey) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        ChatCompletionRequestDto.MessageDto message = new ChatCompletionRequestDto.MessageDto();
        message.setRole("user");
        message.setContent(question);

        ChatCompletionRequestDto requestDto = new ChatCompletionRequestDto();
        requestDto.setModel("gpt-3.5-turbo");
        requestDto.setMessages(Collections.singletonList(message));

        HttpEntity<ChatCompletionRequestDto> requestEntity = new HttpEntity<>(requestDto, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
            apiUrl,
            HttpMethod.POST,
            requestEntity,
            String.class
        );

        String responseBody = responseEntity.getBody();

        // JSON 문자열을 ChatCompletionResponseDto로 역직렬화
        ObjectMapper objectMapper = new ObjectMapper();
        ChatCompletionResponseDto responseDto;
        try {
            responseDto = objectMapper.readValue(responseBody, ChatCompletionResponseDto.class);
        } catch (JsonProcessingException e) {
            // 역직렬화 실패 처리
            e.printStackTrace();
            return null;
        }

        return responseDto;
    }
}
