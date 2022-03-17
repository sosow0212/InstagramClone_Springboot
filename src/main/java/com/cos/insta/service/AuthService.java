package com.cos.insta.service;

import com.cos.insta.domain.user.User;
import com.cos.insta.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor // final이 붙은 모든 것의 생성자를 만들어줌
@Service // 1. IoC 등록 ,  2. 트랜잭션 관리
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional // Write(Insert, Update, Delete)
    public User 회원가입(User user) {
        // 회원가입 진행
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword); // 해쉬 암호화
        user.setPassword(encPassword);
        user.setRole("ROLE_USER"); // 회원가입하면 권한을 부여함, 관리자는 ROLE_ADMIN
        User userEntity = userRepository.save(user);
        return userEntity;

    }
}
