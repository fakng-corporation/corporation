create table role
(
    id          bigserial unique primary key,
    title       varchar(64)   not null,
    description varchar(4096) not null,
    project_id  bigint,
    created_at  timestamp DEFAULT current_timestamp,
    updated_at  timestamp DEFAULT current_timestamp,
    FOREIGN KEY (project_id) REFERENCES "project" (id) ON DELETE CASCADE
);