CREATE TABLE post (
    id  BIGSERIAL PRIMARY KEY,
    title varchar(64) not null,
    description varchar(10240) not null,
    created_at timestamptz DEFAULT current_timestamp,
    updated_at timestamptz DEFAULT current_timestamp
)
