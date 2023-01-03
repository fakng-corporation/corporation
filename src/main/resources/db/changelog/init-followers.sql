CREATE TABLE followers
(
    id          BIGSERIAL PRIMARY KEY,
    followee_id BIGINT,
    follower_id BIGINT,
    FOREIGN KEY (followee_id) REFERENCES "user" (id) ON DELETE CASCADE,
    FOREIGN KEY (follower_id) REFERENCES "user" (id) ON DELETE CASCADE
)