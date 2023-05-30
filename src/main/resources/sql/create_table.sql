CREATE TABLE author (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);
CREATE TABLE book (
     id SERIAL PRIMARY KEY,
     title VARCHAR(100) NOT NULL,
     author_id INTEGER NOT NULL REFERENCES author(id),
     published_date DATE NOT NULL,
     price NUMERIC(8,2) NOT NULL
);

CREATE TABLE Users (
                       user_name text unique not null,
                       id BIGSERIAL PRIMARY KEY,
                       password text not null
);