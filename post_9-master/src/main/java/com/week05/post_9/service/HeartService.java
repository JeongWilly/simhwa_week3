package com.week05.post_9.service;

import com.week05.post_9.model.Heart;
import com.week05.post_9.model.Post;
import com.week05.post_9.model.User;
import com.week05.post_9.repository.HeartRepository;
import com.week05.post_9.repository.PostRepository;
import com.week05.post_9.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public HashMap<String, Object> ReadHeart(Long post_id, Long user_id) {
        Heart heart = heartRepository.findByPostIdAndUsertId(post_id, user_id);
        List<Heart> heartCount = heartRepository.findByPostId(post_id);
        HashMap<String, Object> map = new HashMap<>();
        Integer Count = heartCount.size();
        if (heart == null){
            map.put("check", false);
            map.put("heartCount", Count);
            return map;
        }
        map.put("check", true);
        map.put("heartCount", Count);
        return map;
    }

    @Transactional
    public Heart CreateHeart(Long post_id, Long user_id){
        Post post = postRepository.findById(post_id).orElseThrow(
                () -> new IllegalArgumentException("포스트가 존재하지 않습니다.")
        );
        User user = userRepository.findById(user_id).orElseThrow(
                () -> new IllegalArgumentException("계정이 존재하지 않습니다.")
        );
        Heart heart = heartRepository.findByPostIdAndUsertId(post_id, user_id);
        if (heart == null) {
            Heart newHeart = new Heart();
            newHeart.setHeart(true);
            post.addHeart(newHeart);
            user.addHeart(newHeart);
            heartRepository.save(newHeart);
            return newHeart;
        }
        return null;
    }

    public Heart DeleteHeart(Long post_id, Long user_id){
        Post post = postRepository.findById(post_id).orElseThrow(
                () -> new IllegalArgumentException("포스트가 존재하지 않습니다.")
        );
        User user = userRepository.findById(user_id).orElseThrow(
                () -> new IllegalArgumentException("계정이 존재하지 않습니다.")
        );
        Heart heart = heartRepository.findByPostIdAndUsertId(post_id, user_id);
        if (heart != null){
            post.deleteHeart(heart);
            user.deleteHeart(heart);
            heartRepository.deleteById(heart.getId());
            return heart;
        }
        return null;
    }
}