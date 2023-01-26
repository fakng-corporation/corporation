create table user_team (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    user_id bigint not null,
    team_id bigint not null,
    constraint userid_fk foreign key (user_id) references "user"(id),
    constraint teamid_fk foreign key (team_id) references team(id)
);