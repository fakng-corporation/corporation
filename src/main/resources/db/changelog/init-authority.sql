CREATE TABLE authority (
    id SMALLSERIAL PRIMARY KEY,
    authority VARCHAR(64) NOT NULL UNIQUE
);

INSERT INTO authority (authority) VALUES ('ROLE_USER');

ALTER TABLE "user"
ADD COLUMN enabled BOOLEAN NOT NULL DEFAULT TRUE,
ADD COLUMN authority_id SMALLINT DEFAULT 1,
ADD CONSTRAINT fk_authority
    FOREIGN KEY (authority_id)
        REFERENCES authority (id);

CREATE UNIQUE INDEX ON "user"(nickname);