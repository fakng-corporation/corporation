INSERT INTO project(title, description)
VALUES
    ('facebook', 'We want to create facebook! Join us!'),
    ('telegram', 'We want to create telegram! Join us!');

INSERT INTO skill(title)
VALUES
    ('java'),
    ('javascript'),
    ('postgresql'),
    ('monogodb'),
    ('git'),
    ('maven'),
    ('junit');

INSERT INTO "user" (nickname, email, password, about_me)
VALUES
    ('boba', 'boba@boba.com', '1234', 'I am boba!'),
    ('kate', 'kate@kate.com', '1234', 'I am kate!'),
    ('mary', 'mary@mary.com', '1234', 'I am mary!'),
    ('olaf', 'olaf@olaf.com', '1234', 'I am olaf!'),
    ('vova', 'vova@vova.com', '1234', 'I am vova!');

INSERT INTO users_projects(user_id, project_id)
VALUES
    (1, 1),
    (2, 1),
    (3, 1),
    (3, 2),
    (4, 2);

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