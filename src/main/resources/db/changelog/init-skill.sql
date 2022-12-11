CREATE TABLE skill (
    id int GENERATED ALWAYS AS IDENTITY UNIQUE,
    title varchar(30)
);

INSERT INTO skill(title)
VALUES
    ('java'),
    ('javascript'),
    ('postgresql'),
    ('monogodb'),
    ('git'),
    ('maven'),
    ('junit');