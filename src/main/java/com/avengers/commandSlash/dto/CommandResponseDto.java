/*
 * Copyright 2023 NHN (https://nhn.com) and others.
 * © NHN Corp. All rights reserved.
 */

package com.avengers.commandSlash.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 자세한 기능을 적어주세요.
 *
 * <p><b>Example:</b> 사용법을 간단히 적어주세요</p>
 *
 * <pre class="code">
 *  Demo demo = new Demo();
 *  demo.print("Hello Nhn");
 * </pre>
 *
 * @author ys.ko
 * @see com.example.demo.DemoApplication
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandResponseDto {

    private String text;
    private String responseType = "inChannel";

    public CommandResponseDto(String text) {
        this.text = text;
    }
}
