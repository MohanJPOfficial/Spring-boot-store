ALTER TABLE users DROP COLUMN state;

ALTER TABLE addresses
    ADD state varchar(255) NOT NULL;