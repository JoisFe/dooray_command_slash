package com.avengers.commandSlash.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatCompletionResponseDto {
    private String id;
    private String object;
    private long created;
    private String model;
    private List<ChoiceDto> choices;
    private UsageDto usage;

    // 생성자, getter, setter 생략

    @Data
    public static class ChoiceDto {
        private int index;
        private String finish_reason;
        private MessageDto message;

        // 생성자, getter, setter 생략
    }

    @Data
    public static class MessageDto {
        private String role;
        private String content;

        // 생성자, getter, setter 생략
    }

    @Data
    static class UsageDto {
        private int prompt_tokens;
        private int completion_tokens;
        private int total_tokens;
    }
}
