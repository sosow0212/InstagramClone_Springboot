package com.cos.photogramstart.domain.image;

import com.cos.photogramstart.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String caption;
    private String postImageUrl; // 사진을 전송 받아서 그 사진을 서버 특정 폴더에 저장 - DB에 그 저장된 경로를 INSERT

    @JsonIgnoreProperties({"images"})
    @JoinColumn(name="userId") // foriegn 키 이름 지정
    @ManyToOne(fetch = FetchType.EAGER) // 이미지를 Select하면 조인해서 user 정보를 같이 들고옴
    private User user; // User : Image = 1 : N , ==> ManyToOne

    // 이미지 좋아요

    // 댓글

    private LocalDateTime createDate;

    @PrePersist
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
