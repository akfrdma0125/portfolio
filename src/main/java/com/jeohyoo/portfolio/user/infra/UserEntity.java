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
    private Long id;

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

    public static User toModel(UserEntity userEntity){
        return User.builder()
                .userId(userEntity.id)
                .email(userEntity.email)
                .nickname(userEntity.nickname)
                .createdTime(userEntity.createdTime)
                .build();
    }

}
