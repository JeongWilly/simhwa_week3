package com.week05.post_9.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommentUpdateDto {
    private Optional<String> content;
}
