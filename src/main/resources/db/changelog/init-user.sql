-- user is a reserved word in postgresql
CREATE TABLE "user" (
    id int GENERATED ALWAYS AS IDENTITY UNIQUE,
    nickname varchar(30),
    email varchar(40),
    password varchar(30),
    about_me varchar(120)
);

INSERT INTO "user" (nickname, email, password, about_me)
VALUES
    ('boba', 'boba@boba.com', '1234', 'I am boba!'),
    ('kate', 'kate@kate.com', '1234', 'I am kate!'),
    ('mary', 'mary@mary.com', '1234', 'I am mary!'),
    ('olaf', 'olaf@olaf.com', '1234', 'I am olaf!'),
    ('vova', 'vova@vova.com', '1234', 'I am vova!');