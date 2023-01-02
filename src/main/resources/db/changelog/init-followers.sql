CREATE TABLE followers (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT,
    follower_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES "user"(id) ON DELETE CASCADE,
    FOREIGN KEY (follower_id) REFERENCES "user"(id) ON DELETE CASCADE
)