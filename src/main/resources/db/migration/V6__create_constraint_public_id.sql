ALTER TABLE crud_tables.crud_users
    ADD CONSTRAINT IF NOT EXISTS public_id_unique UNIQUE (public_id);