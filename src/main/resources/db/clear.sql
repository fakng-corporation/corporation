delete from DATABASECHANGELOG;
drop sequence if exists user_seq;
drop table if exists "user";
drop table if exists project;
drop table if exists skill;
drop table if exists users_skills;
drop table if exists users_projects;