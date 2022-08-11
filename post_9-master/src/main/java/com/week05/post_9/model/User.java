package com.week05.post_9.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter//get함수 일괄적으로 생성
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {


    //nullable : null허용여부
    //unique : 중복허용여부(false일때 중복허용)
    @Id
    @Column(nullable = false)
    private Long id;

    @Column(nullable=false, unique=true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value=EnumType.STRING) // 값을 저장할때는 enum이 아닌 STRING으로 저장함
    private UserAuthorityEnum authority;


    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private Set<Heart> heart = new HashSet<>();

    public User(String username,String password,UserAuthorityEnum authority){
        this.username=username;
        this.password=password;
        this.authority=authority;

    }

//    @OneToMany(mappedBy = "", orphanRemoval = true, cascade = CascadeType.REMOVE)
//    @OrderBy("id asc")
//    @Builder.Default
//    @JsonManagedReference
//    private List<Comment> comments = new ArrayList<>();
//
//    public void addComment(Comment comment){
//        //comment의 writer 설정은 comment에서 함
//        comments.add(comment);
//    }
    public void addHeart(Heart heart){
        this.heart.add(heart);
        heart.setUser(this);
    }

    public void deleteHeart(Heart heart){
        this.heart.remove(heart);
        heart.setUser(null);
    }

}

