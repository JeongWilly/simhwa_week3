package com.week05.post_9.dto;

import com.week05.post_9.model.Post;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HeartDto {
    private Post post;
    private String userId;
}