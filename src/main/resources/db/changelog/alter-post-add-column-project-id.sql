ALTER TABLE "post" ADD COLUMN project_id bigint REFERENCES "project" (id) ON DELETE CASCADE