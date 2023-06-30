create table users(
    id SERIAL primary key,
    username varchar(45) not null,
	password varchar(45) not null,
	enabled boolean not null
);

create table authorities (
    id SERIAL primary key,
	username varchar(45) not null,
	authority varchar(45) not null
);


INSERT INTO users VALUES (1, 'happy', '12345', '1');
INSER INTO authorities VALUES (2, 'happy', 'write');

DROP TABLE "public"."tb_user";

CREATE TABLE tb_user (
  id SERIAL PRIMARY KEY,
  username VARCHAR(255) NOT NULL UNIQUE,
  email VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255),
  role VARCHAR(255)
);