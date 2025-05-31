ALTER TABLE crud_tables.crud_users
    ALTER COLUMN public_id SET DEFAULT
        (nextval('crud_tables.public_id_sequence') * 1000000) +
        floor(random() * 1000000);