package com.cos.insta.domain.user.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {

    @Modifying // INSERT, DELETE, UPDATE 를 네이티브 쿼리로 작성하려면 해당 어노테이션이 필요함
    @Query(value = "INSERT INTO subscribe(fromUserId, toUserId, createDate) VALUES( :fromUserId, :toUserId, now())", nativeQuery = true)
    void mSubScribe(int fromUserId, int toUserId); // 성공 1 리턴, 실패 -1 리턴, 10번 성공이면 10

    @Modifying
    @Query(value = "DELETE FROM subscribe WHERE fromUserId =:fromUserId AND toUserId =:toUserId", nativeQuery = true)
    void mUnSubscribe(int fromUserId, int toUserId); // 성공 1 리턴, 실패 -1 리턴

    @Query(value = "SELECT COUNT(*) FROM subscribe WHERE fromUserId = :principalId AND toUserID = :pageUserId", nativeQuery = true)
    int mSubscribeState(int principalId, int pageUserId);

    @Query(value = "SELECT COUNT(*) FROM subscribe WHERE fromUserId = :pageUserId", nativeQuery = true)
    int mSubscribeCount(int pageUserId);
}
