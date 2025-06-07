-- function that ALWAYS generates public_id from NEW.id
CREATE OR REPLACE FUNCTION crud_tables.assign_public_id()
RETURNS trigger AS $$
BEGIN
  -- always overwrites any value that comes in NEW.public_id
  NEW.public_id := (
    NEW.id * 100000
    + trunc(random() * 100000)
  )::varchar;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

--  Remove any previous trigger and create the trigger that invokes assign_public_id() before each INSERT
DROP TRIGGER IF EXISTS set_public_id_trigger ON crud_tables.crud_users;

CREATE TRIGGER set_public_id_trigger
    BEFORE INSERT
    ON crud_tables.crud_users
    FOR EACH ROW
    EXECUTE FUNCTION crud_tables.assign_public_id();