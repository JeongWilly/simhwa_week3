package com.week05.post_9.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.week05.post_9.dto.PostRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")   // FK 컬럼명 설정
    private User user;

    // (nullable = false)을 붙이면 에러 발생
    @Column
    private String title;

    @Column
    private String contents;

    public Post(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.contents = postRequestDto.getContents();
    }

    public void update(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.contents = postRequestDto.getContents();
    }

    @OneToMany(mappedBy = "post", orphanRemoval = true, cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    @Builder.Default
    @JsonManagedReference
    private List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment){
        //comment의 Post 설정은 comment에서 함
        comments.add(comment);
    }



    @JsonIgnore
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Builder.Default
    private Set<Heart> heart = new HashSet<>();

    public void addHeart(Heart heart){
        this.heart.add(heart);
        heart.setPost(this);
    }

    public void deleteHeart(Heart heart){
        this.heart.remove(heart);
        heart.setPost(null);
    }
}

