package com.week05.post_9.controller;

import com.week05.post_9.dto.SignupRequestDto;
import com.week05.post_9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원 로그인 페이지
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/signup")
    public String registerUser(SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
        return "redirect:/login";
    }

    //GetMapping("/메인으로") 로그아웃시 갈곳
}