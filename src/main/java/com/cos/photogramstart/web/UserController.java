package com.cos.photogramstart.web;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

    @GetMapping("/user/{id}")
    public String profile(@PathVariable int id) {
        return "user/profile";
    }

    @GetMapping("/user/{id}/update")
    public String update(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        /**
         *  @AuthenticationPrincipal PrincipalDetails principalDetails
         *  위에 어노테이션을 통해서 세션 정보를 바로 찾을 수 있다.
         *  System.out.println("세션 정보 : " + principalDetails.getUser());
         */

        return "user/update";
    }
}
