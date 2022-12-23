-- user is a reserved word in postgresql
CREATE TABLE "user" (
    id BIGSERIAL PRIMARY KEY,
    nickname varchar(64) UNIQUE NOT NULL,
    email varchar(32) UNIQUE NOT NULL,
    password varchar(128) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT FALSE,
    authority_id SMALLINT DEFAULT 1,
    about_me varchar(4096),
    created_at timestamptz DEFAULT current_timestamp,
    updated_at timestamptz DEFAULT current_timestamp,
    CONSTRAINT fk_authority
        FOREIGN KEY (authority_id)
            REFERENCES authority (id)
);