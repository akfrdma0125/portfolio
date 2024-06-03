package com.jeohyoo.portfolio.comment.infra;


import com.jeohyoo.portfolio.comment.domain.Comment;
import com.jeohyoo.portfolio.post.infra.PostEntity;
import com.jeohyoo.portfolio.user.infra.UserEntity;
import jakarta.persistence.*;
import lombok.Setter;

@Setter
@Entity(name = "comments")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity post;

    @Column(columnDefinition = "varchar(255)")
    private String content;

    @Column
    private Long createdDate;

    public static CommentEntity from(Comment comment){
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setUser(UserEntity.from(comment.getUser()));
        commentEntity.setPost(PostEntity.from(comment.getPost()));
        commentEntity.setContent(comment.getContent());
        commentEntity.setCreatedDate(comment.getCreatedDate());
        return commentEntity;
    }

    public Comment toModel(){
        return Comment.builder()
                .commentId(commentId)
                .user(user.toModel())
                .post(post.toModel())
                .content(content)
                .createdDate(createdDate)
                .build();
    }
}
