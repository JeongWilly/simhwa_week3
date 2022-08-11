package com.week05.post_9.service;

import com.week05.post_9.dto.PostRequestDto;
import com.week05.post_9.model.Post;
import com.week05.post_9.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    // 게시글 작성 기능
    public void createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        postRepository.save(post);
    }

    // 게시글 조회 기능
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    // 게시글 상세 조회 기능
    public Post getEachPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("찾는 포스팅이 존재하지 않습니다")
        );
        return post;
    }

    @Transactional // 업데이트시 db에 반영되도록 해줌
    public Long update(Long id, PostRequestDto requestDto) {
        Post post1 = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        post1.update(requestDto);
        return post1.getId();
    }

    // 게시글 삭제 기능
    public Long deletePost(Long id) {
        postRepository.deleteById(id);
        return id;
    }
}
