package com.beyond.basic.b3_post.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PostDetailDto {
    private Long id;
    private String title;
    private String contents;
    private String memberEmail;
}
