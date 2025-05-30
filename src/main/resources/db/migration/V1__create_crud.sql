CREATE SCHEMA IF NOT EXISTS crud_tables;

CREATE TABLE IF NOT EXISTS crud_tables.crud_users(
    id SERIAL PRIMARY key,
    name TEXT,
    email TEXT,
    password TEXT
);