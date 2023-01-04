alter table project add column IF NOT EXISTS owner_id bigint;
alter table project drop constraint project_title_key;

alter table project add constraint fk_project_user foreign key (owner_id)
    references "user" (id);