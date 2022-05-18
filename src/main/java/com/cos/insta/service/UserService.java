package com.cos.insta.service;

import com.cos.insta.domain.user.User;
import com.cos.insta.domain.user.UserRepository;
import com.cos.insta.domain.user.subscribe.SubscribeRepository;
import com.cos.insta.handler.ex.CustomException;
import com.cos.insta.handler.ex.CustomValidationApiException;
import com.cos.insta.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final SubscribeRepository subscribeRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional(readOnly = true)
    public UserProfileDto 회원프로필(int pageUserId, int principalId) {

        UserProfileDto dto = new UserProfileDto();

        // SELECT * FROM image WHERE userId = :userId;
        User userEntity = userRepository.findById(pageUserId).orElseThrow(() -> {
            throw new CustomException("해당 프로필 페이지는 찾을 수 없습니다.");
        });

        dto.setUser(userEntity);
        dto.setPageOwnerState(pageUserId == principalId);
        dto.setImageCount(userEntity.getImages().size());

        int subscribeState = subscribeRepository.mSubscribeState(principalId, pageUserId);
        int subscribeCount = subscribeRepository.mSubscribeCount(pageUserId);

        dto.setSubscribeState(subscribeState == 1);
        dto.setSubscribeCount(subscribeCount);

        return dto;
    }

    @Transactional // 회원수정이 일어나니 이 어노테이션 붙여야함
    public User 회원수정(int id, User user) {
        // 1. 영속화
        // 1. 무조건 찾았다. 걱정마 get() 2. 못 찾았어 익셉션 발동 orElseThrow
        User userEntity = userRepository.findById(id).orElseThrow(() -> { return new CustomValidationApiException("찾을 수 없는 id입니다.");});

        // 2. 영속화된 오브젝트를 수정 - 더티체킹 (업데이트 완료)
        userEntity.setName(user.getName());

        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        userEntity.setPassword(encPassword);
        userEntity.setBio(user.getBio());
        userEntity.setWebsite(user.getWebsite());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());
        return userEntity;

        // 더티체킹이 일어나서 업데이트 완료됨
    }
}