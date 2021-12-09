package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.domain.user.User;
import lombok.Data;


// jsp 에서 회원정보 수정, update.js

@Data
public class UserUpdateDto {
    private String name; // 필수
    private String password; // 필수
    private String website;
    private String bio;
    private String phone;
    private String gender;

    // 코드 수정이 필요함
    public User toEntity() {
        return User.builder()
                .name(name)
                .password(password)
                .website(website)
                .bio(bio)
                .phone(phone)
                .gender(gender)
                .build();
    }
}
