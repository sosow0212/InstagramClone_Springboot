package com.cos.photogramstart.domain.user.subscribe;

import com.cos.photogramstart.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "subscribe_uk" ,
                        columnNames = {"fromUserId", "toUserId"}
                )
        }
)
public class Subscribe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name="fromUserId")
    @ManyToOne // 유저가 many
    private User fromUser; // 구독 하는 사람

    @JoinColumn(name = "toUserId")
    @ManyToOne
    private User toUser; // 구독 받는 유저

    private LocalDateTime createDate;


    @PrePersist
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

}
