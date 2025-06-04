UPDATE crud_tables.crud_users
SET public_id = (id * 100000 + floor(random() * 100000))::varchar;

ALTER TABLE crud_tables.crud_users
    ALTER COLUMN public_id SET DEFAULT (
        (nextval('crud_tables.public_id_sequence') * 100000 + floor(random() * 100000))::varchar
    );