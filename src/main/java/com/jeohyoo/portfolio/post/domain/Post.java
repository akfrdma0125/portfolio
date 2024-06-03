package com.jeohyoo.portfolio.post.domain;

import com.jeohyoo.portfolio.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Post {
    private Long postId;
    private User writer;
    private String imagePath;
    private String title;
    private String outline;
    private String outcome;
    private String codeUrl;
    private String role;
    private Long startedDate;
    private Long endedDate;
    private String content;
    private Long createdDate;
}
