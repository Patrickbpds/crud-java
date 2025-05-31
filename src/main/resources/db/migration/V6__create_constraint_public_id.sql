DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM pg_constraint
        WHERE conname = 'public_id_unique'
    ) THEN
ALTER TABLE crud_tables.crud_users
    ADD CONSTRAINT public_id_unique UNIQUE (public_id);
END IF;
END$