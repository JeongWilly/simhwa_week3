package com.week05.post_9.repository;

import com.week05.post_9.model.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;



public interface HeartRepository extends JpaRepository<Heart, Long> {

    Heart findByPostIdAndUsertId(Long post_id, Long user_id);

    List<Heart> findByPostId(Long post_id);
}