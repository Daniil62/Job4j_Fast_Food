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