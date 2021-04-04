CREATE TABLE CUSTOMERS (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    document TEXT NOT NULL,
    active boolean default true
);