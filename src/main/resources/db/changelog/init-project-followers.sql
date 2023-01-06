CREATE TABLE project_followers (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    project_id BIGINT NOT NULL,
    follower_id BIGINT NOT NULL,
    FOREIGN KEY ("project_id") REFERENCES "project" (id) ON DELETE CASCADE,
    FOREIGN KEY ("follower_id") REFERENCES "user" (id) ON DELETE CASCADE
)