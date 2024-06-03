package com.jeohyoo.portfolio.user.infra;

import com.jeohyoo.portfolio.common.domain.port.ClockHolder;
import com.jeohyoo.portfolio.user.domain.User;
import jakarta.persistence.*;
import lombok.Setter;

@Setter
@Entity(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(columnDefinition = "varchar(50)")
    private String email;

    @Column(columnDefinition = "varchar(20)")
    private String nickname;

    @Column
    private Long createdTime;

    public static UserEntity from(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setNickname(user.getNickname());
        userEntity.setCreatedTime(user.getCreatedTime());
        return userEntity;
    }

    public User toModel(){
        return User.builder()
                .userId(userId)
                .email(email)
                .nickname(nickname)
                .createdTime(createdTime)
                .build();
    }

}
