create table invite_team (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    sender_id bigint NOT NULL,
    recipient_id bigint NOT NULL,
    team_id bigint NOT NULL,
    code varchar(128) NOT NULL
);