package com.week05.post_9.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.week05.post_9.dto.SignupRequestDto;
import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "heart")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Heart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    private boolean isHeart;


}