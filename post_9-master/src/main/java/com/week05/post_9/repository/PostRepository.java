package com.week05.post_9.repository;

import com.week05.post_9.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findPostByUserId();
}
