package com.jeohyoo.portfolio.user.infra;

import com.jeohyoo.portfolio.user.domain.User;
import jakarta.persistence.*;
import lombok.Setter;

@Setter
@Entity(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(columnDefinition = "varchar(50)")
    private String email;

    @Column(columnDefinition = "varchar(20)")
    private String nickname;

    @Column(name = "created_date")
    private Long createdDate;

    public static UserEntity from(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setNickname(user.getNickname());
        userEntity.setCreatedDate(user.getCreatedDate());
        return userEntity;
    }

    public User toModel(){
        return User.builder()
                .userId(userId)
                .email(email)
                .nickname(nickname)
                .createdDate(createdDate)
                .build();
    }

}
