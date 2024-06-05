package com.jeohyoo.portfolio.user.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {
    private Long userId;
    private String email;
    private String nickname;
    private Long createdDate;
}
