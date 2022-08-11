package com.week05.post_9.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 *단지 heart controller message 를 위한 클래스입니다
 * */

@Getter
@NoArgsConstructor
public class Message {
    private String message1;

    @Builder
    public Message(String message1, String message2){
        this.message1 = message1;
    }
}
