CREATE TABLE comments (
    id  BIGSERIAL PRIMARY KEY,
    created_Date timestamptz DEFAULT current_timestamp,
    author_id bigint,
    post_id bigint,
    content TEXT,
    FOREIGN KEY (author_id) REFERENCES "user"(id) ON DELETE CASCADE,
    FOREIGN KEY (post_id) REFERENCES "post"(id) ON DELETE CASCADE

)