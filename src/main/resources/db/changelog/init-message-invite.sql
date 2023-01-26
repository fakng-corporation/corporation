CREATE TABLE message (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    subject varchar(128) NOT NULL,
    "body" varchar(4096) NOT NULL,
    sender_id bigint REFERENCES "user"(id) ON DELETE CASCADE,
    recipient_id bigint REFERENCES "user"(id) ON DELETE CASCADE
);
create table invite_team (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    sender_id bigint REFERENCES "user"(id) ON DELETE CASCADE NOT NULL,
    recipient_id bigint REFERENCES "user"(id) ON DELETE CASCADE NOT NULL,
    team_id bigint REFERENCES team(id) ON DELETE CASCADE NOT NULL,
    code varchar(36) NOT NULL
);
CREATE INDEX invite_team_code ON invite_team (code);
CREATE INDEX message_recipient_id ON message (recipient_id);
CREATE INDEX message_sender_id ON message (sender_id);