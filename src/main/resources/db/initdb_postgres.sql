DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE users
(
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name        VARCHAR NOT NULL,
  email       VARCHAR NOT NULL,
  password    VARCHAR NOT NULL,
  salt        VARCHAR NOT NULL,
  registered  TIMESTAMP DEFAULT now(),
  enabled     BOOL DEFAULT TRUE,
  version     INTEGER
);

CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles (
  user_id INTEGER NOT NULL,
  email   VARCHAR NOT NULL,
  role    VARCHAR NOT NULL,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX users_unique_email_role_idx ON user_roles (email, role);

CREATE TABLE books (
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  author      TEXT NOT NULL,
  title       TEXT NOT NULL,
  year        INTEGER NOT NULL,
  text        TEXT NOT NULL,
  version     INTEGER
);