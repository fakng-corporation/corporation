CREATE TABLE project(
    id int GENERATED ALWAYS AS IDENTITY UNIQUE,
    title varchar(30),
    description varchar(120)
);

INSERT INTO project(title, description)
VALUES
    ('facebook', 'We want to create facebook! Join us!'),
    ('telegram', 'We want to create telegram! Join us!');