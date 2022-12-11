CREATE TABLE users_skills (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    user_id bigint,
    skill_id bigint,
    CONSTRAINT fk_user
        FOREIGN KEY (user_id)
            REFERENCES "user" (id)
                ON DELETE CASCADE,
    CONSTRAINT fk_skill
        FOREIGN KEY (skill_id)
            REFERENCES skill (id)
                ON DELETE CASCADE
);
