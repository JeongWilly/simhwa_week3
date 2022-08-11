package com.week05.post_9.controller;

import com.week05.post_9.dto.PostRequestDto;
import com.week05.post_9.model.Post;
import com.week05.post_9.model.User;
import com.week05.post_9.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// view 확인을 위한 컨트롤러기 때문에 나중에 우창님께서 바꾸셔도 돼요 : )

@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    // 게시글 작성
    @PostMapping()
    public void createCourse(@RequestBody PostRequestDto requestDto) {
        postService.createPost(requestDto);
    }

    // 게시글 조회
    @GetMapping()
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    // 게시글 상세 조회
    @GetMapping("/{id}")
    public Post getEachPost(@PathVariable Long id) {
        return postService.getEachPost(id);
    }

    // 게시글 수정
    @PutMapping("/{id}")
    public Long updatePost(@PathVariable Long id, PostRequestDto requestDto) {
        return postService.update(id, requestDto);
    }

    // 게시글 삭제
    @DeleteMapping("/{id}")
    public Long deletePost(@PathVariable Long id) {
        return postService.deletePost(id);
    }
}