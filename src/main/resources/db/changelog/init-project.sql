create TABLE project (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    title varchar(128) NOT NULL,
    description varchar(4096),
    owner_id bigint REFERENCES "user" (id) ON delete SET NULL,
    created_at timestamptz DEFAULT current_timestamp,
    updated_at timestamptz DEFAULT current_timestamp
);