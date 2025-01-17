package com.beyond.basic.b3_post.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommonDto {
    private int status_code;
    private String status_message;
    private Object result;
}
