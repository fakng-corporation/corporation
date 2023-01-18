CREATE TABLE project_followers (
    id BIGSERIAL PRIMARY KEY,
    project_id BIGINT NOT NULL,
    follower_id BIGINT NOT NULL,
    followed_at timestamptz default current_timestamp,
    CONSTRAINT projectid_fk FOREIGN KEY ("project_id") REFERENCES "project" (id) ON DELETE CASCADE,
    CONSTRAINT userid_fk FOREIGN KEY ("follower_id") REFERENCES "user" (id) ON DELETE CASCADE
)