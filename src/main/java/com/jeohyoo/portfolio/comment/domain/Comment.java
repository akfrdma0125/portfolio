package com.jeohyoo.portfolio.comment.domain;

import com.jeohyoo.portfolio.post.domain.Post;
import com.jeohyoo.portfolio.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Comment {
    private Long commentId;
    private User user;
    private Post post;
    private String content;
    private Long createdDate;
}
