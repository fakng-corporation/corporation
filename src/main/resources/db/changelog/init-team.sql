CREATE TABLE team (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    title varchar(128) NOT NULL,
    description varchar(4096),
    project_id bigint REFERENCES project(id) ON DELETE SET NULL
);
CREATE INDEX team_project_id_title ON team (project_id, title);