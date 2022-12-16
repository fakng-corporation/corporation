CREATE TABLE IF NOT EXISTS post (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(128) NOT NULL,
    body VARCHAR(8192) NOT NULL,
    created_at TIMESTAMPTZ DEFAULT current_timestamp,
    updated_at TIMESTAMPTZ DEFAULT current_timestamp,
    published_at TIMESTAMPTZ DEFAULT current_timestamp,
    user_id BIGSERIAL NOT NULL references post(id)
);