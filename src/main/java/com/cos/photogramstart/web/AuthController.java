package com.cos.photogramstart.web;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor // final이 걸려있는 모든 생성자를 만들어준다. -> final 필드를 DI 할 때 사용함
@Controller // 1.IoC / 2. 파일을 리턴하는 컨트롤러
public class AuthController {

//    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

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
    // Validation 메이븐 추가 및 적용, Dto에 걸어줌
    @PostMapping("/auth/signup")
    public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) { // Validation 만족 안되면 bindingResult에 에러를 저장
        // log.info(signupDto.toString());

        if(bindingResult.hasErrors()) { // bindingResult에 문제가 있다면 (Valid 불만족시)
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error: bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
                System.out.println("========================");
                System.out.println(error.getDefaultMessage());
                System.out.println("========================");
            }
            throw new CustomValidationException("유효성 검사 실패함", errorMap);
        } else {
            // User 에 signupDto를 넣을 것임.
            User user = signupDto.toEntity();
            User userEntity = authService.회원가입(user);
            System.out.println(userEntity);
        }

        return "auth/signin"; // 회원가입 성공하면 로그인 페이지로 이동
    }

}
