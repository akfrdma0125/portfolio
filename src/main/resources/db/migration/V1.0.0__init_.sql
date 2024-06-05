CREATE TABLE users
(
    user_id            BIGINT AUTO_INCREMENT NOT NULL,
    email              VARCHAR(50)          NOT NULL,
    nickname           VARCHAR(20)          NOT NULL,
    created_date       BIGINT               NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (user_id)
);


CREATE TABLE posts
(
    post_id      BIGINT AUTO_INCREMENT NOT NULL,
    image_path   VARCHAR(50)           NOT NULL,
    title        VARCHAR(50)           NOT NULL,
    content      TEXT                  NULL,
    outline      VARCHAR(100)          NOT NULL,
    outcome      VARCHAR(50)           NOT NULL,
    code_url     VARCHAR(255)          NOT NULL,
    role         VARCHAR(50)           NOT NULL,
    created_date BIGINT                NOT NULL,
    ended_date   BIGINT                NOT NULL,
    started_date BIGINT                NOT NULL,
    user_id      BIGINT                NOT NULL,
    CONSTRAINT pk_posts PRIMARY KEY (post_id)
);

ALTER TABLE posts
    ADD CONSTRAINT FK_POSTS_ON_USERS FOREIGN KEY (user_id) REFERENCES users (user_id);


CREATE TABLE post_logs
(
    post_log_id  BIGINT AUTO_INCREMENT NOT NULL,
    created_date BIGINT                NOT NULL,
    post_id      BIGINT                NOT NULL,
    CONSTRAINT pk_post_logs PRIMARY KEY (post_log_id)
);

ALTER TABLE post_logs
    ADD CONSTRAINT FK_POST_LOGS_ON_POSTS FOREIGN KEY (post_id) REFERENCES posts (post_id);


CREATE TABLE comments
(
    comment_id   BIGINT AUTO_INCREMENT NOT NULL,
    content      VARCHAR(255)          NULL,
    created_date BIGINT                NOT NULL,
    user_id      BIGINT                NOT NULL,
    post_id      BIGINT                NOT NULL,
    CONSTRAINT pk_comments PRIMARY KEY (comment_id)
);

ALTER TABLE comments
    ADD CONSTRAINT FK_COMMENTS_ON_USERS FOREIGN KEY (user_id) REFERENCES users (user_id);
ALTER TABLE comments
    ADD CONSTRAINT FK_COMMENTS_ON_POSTS FOREIGN KEY (post_id) REFERENCES posts (post_id);