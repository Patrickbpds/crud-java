UPDATE crud_tables.crud_users
SET public_id = (nextval('crud_tables.public_id_sequence') * 1000000) +
                floor(random() * 1000000)
WHERE public_id IS NULL;