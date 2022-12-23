-- user is a reserved word in postgresql
CREATE TABLE "user" (
    id BIGSERIAL PRIMARY KEY,
    nickname VARCHAR(64) UNIQUE NOT NULL,
    email VARCHAR(32) UNIQUE NOT NULL,
    password VARCHAR(128) NOT NULL,
    about_me VARCHAR(4096),
    avatar_url VARCHAR(128),
    created_at TIMESTAMPTZ DEFAULT current_timestamp,
    updated_at TIMESTAMPTZ DEFAULT current_timestamp
);