CREATE TABLE users (
  id         BIGINT PRIMARY KEY,
  first_name VARCHAR(255) NOT NULL,
  last_name  VARCHAR(255) NOT NULL,
  phone      VARCHAR(20),
  email      VARCHAR(50)
);

CREATE TABLE accounts (
  id      BIGINT PRIMARY KEY,
  title   VARCHAR(100) NOT NULL,
  balance DOUBLE NOT NULL,
  owner BIGINT       NOT NULL,
  FOREIGN KEY (owner) REFERENCES users
);

INSERT INTO users (id, first_name, last_name, phone, email)
VALUES (1, 'Alexandr', 'Pavkin', '89859387008', 'alexandr300896@gmail.com');

INSERT INTO accounts (id, title, balance, owner) VALUES (1, 'Account #1', 30000, 1);
INSERT INTO accounts (id, title, balance, owner) VALUES (2, 'Account #2', 35000, 1);