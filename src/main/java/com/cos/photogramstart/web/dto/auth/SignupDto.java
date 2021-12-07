package com.cos.photogramstart.web.dto.auth;

import com.cos.photogramstart.domain.user.User;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data // Getter, Setter
public class SignupDto {

    @Size(min = 2, max = 10) // valid 옵션, 2~10자
    @NotBlank
    private String username;
    @NotBlank // valid 옵션, 공백 안됨
    private String password;
    @NotBlank
    private String email;
    @NotBlank
    private String name;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .name(name)
                .build();
    }
}
