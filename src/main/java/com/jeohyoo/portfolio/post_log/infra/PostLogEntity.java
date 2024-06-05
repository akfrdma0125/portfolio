package com.jeohyoo.portfolio.post_log.infra;

import com.jeohyoo.portfolio.post.infra.PostEntity;
import com.jeohyoo.portfolio.post_log.domain.PostLog;
import jakarta.persistence.*;
import lombok.Setter;


@Setter
@Entity(name = "post_logs")
public class PostLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_log_id")
    private Long postLogId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity post;

    @Column(name = "modified_date")
    private Long modifiedDate;

    public static PostLogEntity from(PostLog postLog){
        PostLogEntity postLogEntity = new PostLogEntity();
        postLogEntity.setPost(PostEntity.from(postLog.getPost()));
        postLogEntity.setModifiedDate(postLog.getModifiedDate());
        return postLogEntity;
    }

    public PostLog toModel(){
        return PostLog.builder()
                .postLogId(postLogId)
                .post(post.toModel())
                .modifiedDate(modifiedDate)
                .build();
    }


}
