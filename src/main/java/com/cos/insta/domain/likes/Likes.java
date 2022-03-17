package com.cos.insta.domain.likes;

import com.cos.insta.domain.image.Image;
import com.cos.insta.domain.user.User;
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
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "likes_uk" ,
                        columnNames = {"imageId", "userId"}
                )
        }
)

public class Likes { // N
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name="imageId")
    @ManyToOne
    private Image image; // 1

    // 오류가 터지고 확인하기
    @JoinColumn(name="userId")
    @ManyToOne
    private User user; // 1

    private LocalDateTime createDate;

    @PrePersist
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

}
