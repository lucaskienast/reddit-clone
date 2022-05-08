package com.kienast.reddit.redditclonespringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentsDto {

    private Long commentId;
    private Long postId;
    private Instant createdDate;
    private String text;
    private String username;

}
