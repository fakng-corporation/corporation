create table user_team (
    user_id bigint not null,
    team_id bigint not null,
    primary key (user_id, team_id),
    foreign key (user_id) references "user"(id),
    foreign key (team_id) references team(id)
);