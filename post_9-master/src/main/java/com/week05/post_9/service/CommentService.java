package com.week05.post_9.service;

import com.week05.post_9.dto.comment.CommentResponseDto;
import com.week05.post_9.dto.comment.CommentSaveDto;
import com.week05.post_9.dto.comment.CommentUpdateDto;
import com.week05.post_9.dto.ResponseDto;
import com.week05.post_9.exception.comment.CommentException;
import com.week05.post_9.exception.comment.CommentExceptionType;
import com.week05.post_9.exception.post.PostException;
import com.week05.post_9.exception.post.PostExceptionType;
import com.week05.post_9.model.Comment;
import com.week05.post_9.model.Post;
import com.week05.post_9.repository.CommentRepository;
import java.util.ArrayList;
import java.util.List;

import com.week05.post_9.repository.PostRepository;
import com.week05.post_9.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // 예외처리는 회의 후에 적용예정 우선은 각각 적용

    /* CREATE */
    public void commentSave(Long postId , CommentSaveDto commentSaveDto) {
        Comment comment = commentSaveDto.toEntity();

//        comment.confirmUser(userRepository.findByUsername(SecurityUtil.getLoginUsername()).orElseThrow(() ->
//                new UserException(UserExceptionType.NOT_FOUND_MEMBER)));

        comment.confirmPost(postRepository.findById(postId).orElseThrow(() ->
                new PostException(PostExceptionType.POST_NOT_POUND)));


        commentRepository.save(comment);
    }

    public void saveReComment(Long postId, Long parentId, CommentSaveDto commentSaveDto) {
        Comment comment = commentSaveDto.toEntity();

//        comment.confirmUser(userRepository.findByUsername(SecurityUtil.getLoginUsername()).orElseThrow(() ->
//                new UserException(UserExceptionType.NOT_FOUND_MEMBER)));

        comment.confirmParent(commentRepository.findById(parentId).orElseThrow(() ->
                new CommentException(CommentExceptionType.NOT_POUND_COMMENT)));

        commentRepository.save(comment);

    }

    public void update(Long id, CommentUpdateDto commentUpdateDto) {

        Comment comment = commentRepository.findById(id).orElseThrow(() ->
                new CommentException(CommentExceptionType.NOT_POUND_COMMENT));
//        if(!comment.getUser().getUsername().equals(SecurityUtil.getLoginUsername())){
//            throw new CommentException(CommentExceptionType.NOT_AUTHORITY_UPDATE_COMMENT);
//        }

        commentUpdateDto.getContent().ifPresent(comment::updateContent);
    }

    public Long delete(Long id) {
        commentRepository.deleteById(id);
        return id;
    }

    public void remove(Long id) throws CommentException {
        Comment comment = commentRepository.findById(id).orElseThrow(() ->
                new CommentException(CommentExceptionType.NOT_POUND_COMMENT));

//        if(!comment.getUser().getUsername().equals(SecurityUtil.getLoginUsername())){
//            throw new CommentException(CommentExceptionType.NOT_AUTHORITY_DELETE_COMMENT);
//        }

        comment.remove();
        List<Comment> removableCommentList = comment.findRemovableList();
        commentRepository.deleteAll(removableCommentList);
    }


    /* READ */
    @Transactional(readOnly = true)
    public ResponseDto<?> getAllCommentsByPost(Long postId) {

        Post post = postRepository.findById(postId).orElseThrow(() ->
                new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다." + postId));

        List<Comment> commentList = commentRepository.findAllByPost(post);
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();

        for (Comment comment : commentList) {
            commentResponseDtoList.add(
                    CommentResponseDto.builder()
                            .id(comment.getId())
                            .author(comment.getUser().getUsername())
                            .content(comment.getContent())
                            .createdAt(comment.getCreatedAt())
                            .modifiedAt(comment.getModifiedAt())
                            .build()
            );
        }
        return ResponseDto.success(commentResponseDtoList);
    }


    public Comment getComment(Long id) {
        return commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("찾는 포스팅이 존재하지 않습니다")
        );
    }

}
