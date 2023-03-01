CREATE TABLE "like"(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    user_id bigint REFERENCES "user"(id) ON DELETE CASCADE,
    post_id bigint REFERENCES "post"(id) ON DELETE CASCADE
);

CREATE TABLE post_statistics(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    post_id bigint REFERENCES "post"(id) ON DELETE CASCADE,
    likes bigint DEFAULT 0,
    views bigint DEFAULT 0,
    comment_amount bigint DEFAULT 0
);