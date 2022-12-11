CREATE TABLE project(
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    title varchar(128) UNIQUE NOT NULL,
    description varchar(4096)
);