package com.week05.post_9.controller;

import com.week05.post_9.dto.comment.CommentSaveDto;
import com.week05.post_9.dto.comment.CommentUpdateDto;
import com.week05.post_9.model.Comment;
import com.week05.post_9.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/post")
@RestController
public class CommentController {

    private final CommentService commentService;

    // 댓글 조회
    @GetMapping("comment/{id}")
    public Comment getComment(@PathVariable("id") Long id) {
        return commentService.getComment(id);
    }

    @PostMapping("/comment/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void commentSave(@PathVariable("postId") Long postId,@RequestBody CommentSaveDto commentSaveDto){
        commentService.commentSave(postId, commentSaveDto);
    }


    @PostMapping("/comment/{postId}/{commentId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void reCommentSave(@PathVariable("postId") Long postId,
                              @PathVariable("commentId") Long commentId,
                              @RequestBody CommentSaveDto commentSaveDto){
        commentService.saveReComment(postId, commentId, commentSaveDto);
    }


    @PutMapping("/comment/{commentId}")
    public void update(@PathVariable("commentId") Long commentId,
                       @RequestBody CommentUpdateDto commentUpdateDto){
        commentService.update(commentId, commentUpdateDto);
    }


    @DeleteMapping("/comment/{commentId}")
    public void delete(@PathVariable("commentId") Long commentId){
        commentService.delete(commentId);
    }
}
