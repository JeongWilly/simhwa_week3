package com.week05.post_9.dto.comment;

import com.week05.post_9.model.Comment;
import com.week05.post_9.model.Post;
import com.week05.post_9.model.User;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CommentRequestDto {
    private Long postId;
    private String content;


//    private Long id;
//    private String content;
//    private User user;
//    private Post post;
//
//    public Comment toEntity() {
//        Comment comments = Comment.builder()
//                .id(id)
//                .content(content)
//                .user(user)
//                .post(post)
//                .build();
//        return comments;
//    }


}
