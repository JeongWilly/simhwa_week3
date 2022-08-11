package com.week05.post_9.dto.comment;

import com.week05.post_9.model.Comment;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentSaveDto {

    private String content;

    public Comment toEntity() {
        return Comment.builder().content(content).build();
    }
}