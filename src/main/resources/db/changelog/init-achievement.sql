CREATE TABLE achievement (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    title varchar(32) NOT NULL,
    description varchar(4096),
    project_id bigint NOT NULL,
    FOREIGN KEY (project_id) REFERENCES "project" (id)
);