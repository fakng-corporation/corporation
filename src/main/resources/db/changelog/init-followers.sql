CREATE TABLE followers
(
    follower_id BIGINT NOT NULL,
    followee_id BIGINT NOT NULL,
    PRIMARY KEY (follower_id, followee_id),
    CONSTRAINT "followers" FOREIGN KEY (follower_id) REFERENCES "user" (id) ON DELETE CASCADE,
    CONSTRAINT "followees" FOREIGN KEY (followee_id) REFERENCES "user" (id) ON DELETE CASCADE
)