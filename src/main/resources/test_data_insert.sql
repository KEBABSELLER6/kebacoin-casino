INSERT INTO users (firstName,lastName,username,email,birthDate,balance,password,enabled) VALUES ('asd','er','asder','asder@gmail.com','1994-02-21',10000,'asder',1);
INSERT INTO users (firstName,lastName,username,email,birthDate,balance,password,enabled) VALUES ('Mik','Robert','mikrobi','mikrobi@gmail.com','1996-12-01',10000,'dPgbYa',1);
INSERT INTO users (firstName,lastName,username,email,birthDate,balance,password,enabled) VALUES ('Teszt','Joska','testjoska','testjozsi@gmail.com','1999-08-12',10000,'hAEgpp',1);
INSERT INTO users (firstName,lastName,username,email,birthDate,balance,password,enabled) VALUES ('Okos','Béla','okibela','okibela@gmail.com','1990-03-23',10000,'x4JOAL',1);
INSERT INTO users (firstName,lastName,username,email,birthDate,balance,password,enabled) VALUES ('Admin','Admin','admin','admin@gmail.com','1990-03-23',0,'admin',1);


INSERT INTO authorities (NAME) VALUES ('ROLE_USER');
INSERT INTO authorities (NAME) VALUES ('ROLE_ADMIN');

INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (0, 0);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (1, 0);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (2, 0);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (3, 0);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (4, 0);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (4, 1);



