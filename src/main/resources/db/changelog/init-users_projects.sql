CREATE TABLE users_projects (
    user_id int REFERENCES "user"(id),
    project_id int references project(id),
    PRIMARY KEY(user_id, project_id)
);

INSERT INTO users_projects(user_id, project_id)
VALUES
    (1, 1),
    (2, 1),
    (3, 1),
    (3, 2),
    (4, 2);