CREATE TABLE users_skills (
    user_id int REFERENCES "user"(id),
    skill_id int references skill(id),
    PRIMARY KEY(user_id, skill_id)
);

INSERT INTO users_skills(user_id, skill_id)
VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (2, 4),
    (3, 5),
    (3, 6),
    (4, 1),
    (4, 2),
    (4, 3);
