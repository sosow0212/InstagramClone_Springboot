package com.cos.photogramstart.web;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor // final이 걸려있는 모든 생성자를 만들어준다. -> final 필드를 DI 할 때 사용함
@Controller // 1.IoC / 2. 파일을 리턴하는 컨트롤러
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

//    public AuthController(AuthService authService) {
//        this.authService = authService;
//    }


    @GetMapping("/auth/signin")
    public String signinForm() {
        return "auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signupForm() {
        return "auth/signup";
    }


    // 회원가입 처리
    @PostMapping("/auth/signup")
    public String signup(SignupDto signupDto) { // key=value (x-www-form-urlencoded 방식)
        // log.info(signupDto.toString());

        // User 에 signupDto를 넣을 것임.
        User user = signupDto.toEntity();

        User userEntity = authService.회원가입(user);
        System.out.println(userEntity);

        return "auth/signin"; // 회원가입 성공하면 로그인 페이지로 이동
    }

}
