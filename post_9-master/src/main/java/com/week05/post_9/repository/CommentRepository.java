package com.week05.post_9.repository;

import com.week05.post_9.model.Comment;
import com.week05.post_9.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPost(Post post);
    Optional<Comment> findById(Long id);
}
