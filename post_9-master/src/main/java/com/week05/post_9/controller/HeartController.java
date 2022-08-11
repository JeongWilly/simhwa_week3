package com.week05.post_9.controller;

import com.week05.post_9.model.Heart;
import com.week05.post_9.model.Message;
import com.week05.post_9.service.HeartService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Builder
@RestController
@RequiredArgsConstructor
public class HeartController {

    private final HeartService heartService;

    @GetMapping("/post/{post_id}/heart")
    public HashMap<String, Object> ReadHeart(@PathVariable Long post_id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return heartService.ReadHeart(post_id, userDetails.getUser().getId());
    }

    @PostMapping("/post/{post_id}/heart")
    public ResponseEntity CreateHeart(@PathVariable Long post_id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        Heart heart = heartService.CreateHeart(post_id, userDetails.getUser().getId());
        if (heart == null){
            var message = Message.builder()
                    .message1("이미 좋아요 상태입니다.")
                    .build();
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/post/{post_id}/heart")
    public ResponseEntity DeleteHeart(@PathVariable Long post_id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        Heart heart = heartService.DeleteHeart(post_id, userDetails.getUser().getId());
        if (heart == null){
            var message = Message.builder()
                    .message1("취소할 좋아요가 없습니다.")
                    .build();
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().build();
    }

}