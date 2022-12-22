ALTER TABLE "user" ADD enabled BOOLEAN NOT NULL DEFAULT FALSE;
ALTER TABLE "user" ADD authority VARCHAR(64) NOT NULL DEFAULT 'USER';
ALTER TABLE "user" ADD CONSTRAINT fk_authority
FOREIGN KEY (authority) references authority (authority);