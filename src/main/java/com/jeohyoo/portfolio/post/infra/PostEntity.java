package com.jeohyoo.portfolio.post.infra;

import com.jeohyoo.portfolio.post.domain.Post;
import com.jeohyoo.portfolio.user.infra.UserEntity;
import jakarta.persistence.*;
import lombok.Setter;


@Setter
@Entity(name = "posts")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity writer;

    @Column(name = "image_path", columnDefinition = "varchar(255)")
    private String imagePath;

    @Column(columnDefinition = "varchar(50)")
    private String title;

    @Column(columnDefinition = "varchar(100)")
    private String outline;

    @Column(columnDefinition = "varchar(255)")
    private String outcome;

    @Column(name = "code_url", columnDefinition = "varchar(255)")
    private String codeUrl;

    @Column(columnDefinition = "varchar(50)")
    private String role;

    @Column(name = "started_date")
    private Long startedDate;

    @Column(name = "ended_date")
    private Long endedDate;

    @Column(columnDefinition = "text")
    private String content;

    @Column(name = "created_date")
    private Long createdDate;

    public static PostEntity from(Post post){
        PostEntity postEntity = new PostEntity();
        postEntity.setWriter(UserEntity.from(post.getWriter()));
        postEntity.setImagePath(post.getImagePath());
        postEntity.setTitle(post.getTitle());
        postEntity.setOutline(post.getOutline());
        postEntity.setOutcome(post.getOutcome());
        postEntity.setCodeUrl(post.getCodeUrl());
        postEntity.setRole(post.getRole());
        postEntity.setStartedDate(post.getStartedDate());
        postEntity.setEndedDate(post.getEndedDate());
        postEntity.setContent(post.getContent());
        postEntity.setCreatedDate(post.getCreatedDate());
        return postEntity;
    }

    public Post toModel(){
        return Post.builder()
                .writer(writer.toModel())
                .imagePath(imagePath)
                .title(title)
                .outline(outline)
                .outcome(outcome)
                .codeUrl(codeUrl)
                .role(role)
                .startedDate(startedDate)
                .endedDate(endedDate)
                .content(content)
                .createdDate(createdDate)
                .build();
    }
}
