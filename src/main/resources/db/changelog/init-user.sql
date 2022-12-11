-- user is a reserved word in postgresql
CREATE TABLE "user" (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    nickname varchar(64) UNIQUE NOT NULL,
    email varchar(32) UNIQUE NOT NULL,
    password varchar(128) NOT NULL,
    about_me varchar(4096)
);