
CREATE TABLE tb_user (
  id SERIAL PRIMARY KEY,
  username VARCHAR(255) NOT NULL UNIQUE,
  email VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255),
  role VARCHAR(255)
);

CREATE TABLE tb_authorities
(
    id SERIAL NOT NULL,
    user_id bigint NOT NULL,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY(id),
    CONSTRAINT authorities_fkey FOREIGN KEY(user_id)
        REFERENCES public.tb_user(id)
);
