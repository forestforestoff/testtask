DROP TABLE IF EXISTS goods;

CREATE TABLE goods
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR NOT NULL,
    transaction VARCHAR,
    amount      INTEGER,
    date        DATE,
    price       INTEGER
);