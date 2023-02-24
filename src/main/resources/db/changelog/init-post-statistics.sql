CREATE TABLE post_statistics(
    post_id bigint PRIMARY KEY REFERENCES "post"(id) ON DELETE CASCADE,
    likes bigint DEFAULT 0,
    views bigint DEFAULT 0,
    comment_amount bigint DEFAULT 0
)