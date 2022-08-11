package com.week05.post_9.service;

import com.week05.post_9.dto.SignupRequestDto;
import com.week05.post_9.model.User;
import com.week05.post_9.model.UserAuthorityEnum;
import com.week05.post_9.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(SignupRequestDto requestDto) {
// 회원 ID 중복 확인
        String username = requestDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }

// 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());


// 사용자 ROLE 확인
        UserAuthorityEnum authority = UserAuthorityEnum.USER;
        if (requestDto.isAdmin()) {
            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            authority = UserAuthorityEnum.ADMIN;
        }

        User user = new User(username, password, authority);
        userRepository.save(user);
    }
}