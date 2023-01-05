CREATE TABLE review (
  id bigserial primary key,
  title varchar(256) not null,
  review varchar(2048) not null,
  author_id bigint not null,
  user_id bigint not null,
  constraint author_id_fk foreign key (author_id) references "user" (id),
  constraint user_id_fk foreign key (user_id) references "user" (id)
);