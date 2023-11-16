package com.avengers.commandSlash.controller;

import com.avengers.commandSlash.dto.CommandRequestDto;
import com.avengers.commandSlash.dto.CommandResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class commandSlashController {

    @Value("${tockenId}")
    private String tokenId;

    @PostMapping("/command")
    public ResponseEntity<CommandResponseDto> command(@RequestBody CommandRequestDto commandRequestDto) {
        log.info(commandRequestDto.toString());

        String text = null;

        switch (commandRequestDto.getCommand()) {
            case "/bomin":
                text = "역시 보민님!";
                break;
            case "/semi":
                text = "역시 세미님!";
                break;
            case "/thread":
                text = "오늘은 글타래로 대체하겠습니다. 해당 글 아래에 글타래로 작성해주세요!";
                break;
            case "/video":
                text = "화상회의 합시다!!";
                break;
            case "/dam":
                text = "담배 피로 gogo!!";
                break;
            default:
                return ResponseEntity.ok(new CommandResponseDto("잘못된 요청"));
        }

        if (!commandRequestDto.getText().trim().isEmpty() && (commandRequestDto.getCommand().equals("/bomin") || commandRequestDto.getCommand().equals("/semi"))) {
            text = commandRequestDto.getText() + " 보단 " + text;
        }

        return ResponseEntity.ok(new CommandResponseDto(text));
    }
}
