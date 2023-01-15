CREATE TABLE message (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    subject varchar(128) NOT NULL,
    "body" varchar(4096) NOT NULL,
    sender_id bigint REFERENCES "user"(id) ON DELETE CASCADE
);
create table invite_team (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    sender_id bigint NOT NULL,
    recipient_id bigint NOT NULL,
    team_id bigint NOT NULL,
    code varchar(128) NOT NULL
);