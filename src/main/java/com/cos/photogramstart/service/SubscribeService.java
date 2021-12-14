package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.user.subscribe.Subscribe;
import com.cos.photogramstart.domain.user.subscribe.SubscribeRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;

    @Transactional
    public void 구독하기(int fromUserId, int toUserId) {
        try {
            subscribeRepository.mSubScribe(fromUserId, toUserId);
        } catch (Exception e) {
            throw new CustomApiException("이미 구독을 하였습니다.");
        }
    }

    @Transactional
    public void 구독취소하기(int fromUserId, int toUserId) {
        subscribeRepository.mUnSubscribe(fromUserId, toUserId);
    }
}
