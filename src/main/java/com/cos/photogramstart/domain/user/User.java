package com.cos.photogramstart.domain.user;

// JPA - Java Persistence API (자바로 데이터를 영구적으로 저장(DB)할 수 있는 API를 제공)

import com.cos.photogramstart.domain.image.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder // SignupDto 클래스에서 데이터를 담을 때 쓰는 어노테이션
@AllArgsConstructor // 전체 생성자
@NoArgsConstructor // 빈 생성자
@Data // Lombok Getter, Setter 등등 생성
@Entity // DB에 테이블을 자동 생성 -> 실행시키면 DB에 User라는 테이블이 생성된 걸 볼 수 있다.
public class User {

    @Id // Primary Key = pk = id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 데이터베이스를 따라간다.
    private int id;

    @Column(length = 20, unique = true) // varchar(20), username은 중복이 안된다.
    private String username;

    @Column(nullable = false) // null 불가능
    private String password;

    @Column(nullable = false) // null 불가능
    private String name;
    private String website; // 웹 사이트
    private String bio; // 자기 소개
    @Column(nullable = false) // null 불가능
    private String email;
    private String phone;
    private String gender;

    private String profileImageUrl; // 사용자 사진
    private String role; // 권한

    // mappedBy = 나는 연관관계의 주인이 아니다. 그러므로 테이블의 컬럼을 만들지 말라는 뜻
    // User를 SELECT할 때 해당 User id로 등록된 image들을 다 가져오라는 뜻
    // LAZY = User를 SELECT할 때, 해당 User id로 등록된 image들을 가져오지 말라는 뜻, 대신 getImage() 함수의 image들이 호출할 때 가져옴
    // EAGER = User를 SELECT할 때, 해당 User id로 등록된 image들을 전부 JOIN 해서 가져오라는 뜻
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"user"})
    private List<Image> images; // 양방향 매핑

    private LocalDateTime createDate; // 날짜

    @PrePersist // DB에 INSERT 되기 직전에 실행. 즉 DB에 값을 넣으면 자동으로 실행됨
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
