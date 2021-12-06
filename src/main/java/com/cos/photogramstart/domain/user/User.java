package com.cos.photogramstart.domain.user;

// JPA - Java Persistence API (자바로 데이터를 영구적으로 저장(DB)할 수 있는 API를 제공)

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor // 전체 생성자
@NoArgsConstructor // 빈 생성자
@Data // Lombok Getter, Setter 등등 생성
@Entity // DB에 테이블을 자동 생성 -> 실행시키면 DB에 User라는 테이블이 생성된 걸 볼 수 있다.
public class User {

    @Id // Primary Key = pk = id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 데이터베이스를 따라간다.
    private int id;

    private String username;
    private String password;

    private String name;
    private String website; // 웹 사이트
    private String bio; // 자기 소개
    private String email;
    private String phone;
    private String gender;

    private String profileImageUrl; // 사용자 사진
    private String role; // 권한

    private LocalDateTime createDate; // 날짜

    @PrePersist // DB에 INSERT 되기 직전에 실행. 즉 DB에 값을 넣으면 자동으로 실행됨
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
