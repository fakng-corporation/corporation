CREATE TABLE users_projects (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    user_id bigint,
    project_id bigint,
    CONSTRAINT fk_user
        FOREIGN KEY (user_id)
            REFERENCES "user" (id)
                ON DELETE CASCADE,
    CONSTRAINT fk_project
        FOREIGN KEY (project_id)
            REFERENCES project (id)
                ON DELETE CASCADE
);