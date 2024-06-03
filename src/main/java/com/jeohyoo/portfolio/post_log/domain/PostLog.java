package com.jeohyoo.portfolio.post_log.domain;

import com.jeohyoo.portfolio.post.domain.Post;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostLog {
    private Long postLogId;
    private Post post;
    private Long modifiedDate;
}
