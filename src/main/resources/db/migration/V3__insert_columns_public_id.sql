ALTER TABLE crud_tables.crud_users
    ADD COLUMN IF NOT EXISTS public_id BIGINT;