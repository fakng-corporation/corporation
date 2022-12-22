CREATE TABLE authority (
    authority VARCHAR(64) NOT NULL PRIMARY KEY
);

-- Нужно, потому что по дефолту хотим проставить значения
-- в новую колонку authority в таблице user
INSERT INTO authority (authority)
VALUES ('USER')