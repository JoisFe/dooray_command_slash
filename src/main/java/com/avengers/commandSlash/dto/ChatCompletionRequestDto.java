
package com.avengers.commandSlash.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatCompletionRequestDto {
    private String model;
    private List<MessageDto> messages;


    @Data
    public static class MessageDto {
        private String role;
        private String content;
    }
}
