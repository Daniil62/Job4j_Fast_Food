CREATE TABLE IF NOT EXISTS discount(
     id SERIAL PRIMARY KEY,
     _begin TIMESTAMP,
     _before TIMESTAMP,
     _percent INT,
     _value DOUBLE PRECISION
);

CREATE TABLE IF NOT EXISTS dish(
     id SERIAL PRIMARY KEY,
     name VARCHAR,
     in_stock BOOL,
     price DOUBLE PRECISION
);

CREATE TABLE IF NOT EXISTS address(
     id SERIAL PRIMARY KEY,
     street VARCHAR,
     home VARCHAR,
     apartment VARCHAR
);

CREATE TABLE IF NOT EXISTS _order(
     id SERIAL PRIMARY KEY,
     address_id INT REFERENCES address(id),
     status VARCHAR,
     created TIMESTAMP,
     total_price DOUBLE PRECISION
);

CREATE TABLE IF NOT EXISTS item(
     id SERIAL PRIMARY KEY,
     name VARCHAR,
     price DOUBLE PRECISION,
     order_id INT REFERENCES _order(id)
)