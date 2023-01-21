CREATE TABLE followers
(
    id BIGSERIAL PRIMARY KEY,
    follower_id BIGINT NOT NULL,
    followee_id BIGINT NOT NULL,
    followed_at timestamptz default current_timestamp,
    CONSTRAINT "follower_fk" FOREIGN KEY (follower_id) REFERENCES "user" (id) ON DELETE CASCADE,
    CONSTRAINT "followee_fk" FOREIGN KEY (followee_id) REFERENCES "user" (id) ON DELETE CASCADE
)