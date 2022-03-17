package com.cos.insta.web;

import com.cos.insta.config.auth.PrincipalDetails;
import com.cos.insta.service.UserService;
import com.cos.insta.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;


    @GetMapping("/user/{pageUserId}")
    public String profile(@PathVariable int pageUserId, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        UserProfileDto dto = userService.회원프로필(pageUserId, principalDetails.getUser().getId());
        model.addAttribute("dto", dto);
        return "user/profile";
    }

    @GetMapping("/user/{id}/update")
    public String update(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        /**
         *  @AuthenticationPrincipal PrincipalDetails principalDetails
         *  위에 어노테이션을 통해서 세션 정보를 바로 찾을 수 있다.
         *  System.out.println("세션 정보 : " + principalDetails.getUser());
         */

//        model.addAttribute("principal", principalDetails.getUser());
        // model을 안 넘겨줘도 security-taglibs 를 통해 세션 정보를 알아서 넘겨줄 수 있다.

        return "user/update";
    }
}
