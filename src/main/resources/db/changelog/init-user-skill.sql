CREATE TABLE user_skill(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    skill_id BIGINT NOT NULL,
    CONSTRAINT user_id_fk
        FOREIGN KEY (user_id)
            REFERENCES "user" (id),
    CONSTRAINT skill_id_fk
        FOREIGN KEY (skill_id)
            REFERENCES skill (id)
);