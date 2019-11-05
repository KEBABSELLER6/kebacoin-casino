DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id INTEGER IDENTITY PRIMARY KEY,
  firstName VARCHAR(30),
  lastName VARCHAR(30),
  username VARCHAR(30),
  email  VARCHAR(50),
  birthDate DATE,
  balance INTEGER,
  password VARCHAR(30)
);