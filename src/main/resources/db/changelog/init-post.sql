CREATE TABLE post (
    id  BIGSERIAL PRIMARY KEY,
    title varchar(128) not null,
    description varchar(10240) not null,
    user_id bigint,
    published boolean,
    created_at timestamptz DEFAULT current_timestamp,
    updated_at timestamptz DEFAULT current_timestamp,
    FOREIGN KEY (user_id) REFERENCES "user"(id) ON DELETE CASCADE
)
