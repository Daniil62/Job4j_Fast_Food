CREATE TABLE IF NOT EXISTS ingredient(
     id SERIAL PRIMARY KEY,
     name VARCHAR UNIQUE
);

CREATE TABLE IF NOT EXISTS dish(
     id SERIAL PRIMARY KEY,
     name VARCHAR UNIQUE
);

CREATE TABLE IF NOT EXISTS dish_ingredient(
     id SERIAL PRIMARY KEY,
     dish_id INT REFERENCES dish(id),
     ingredient_id INT REFERENCES ingredient(id)
);

CREATE TABLE IF NOT EXISTS cooked_dish(
     id SERIAL PRIMARY KEY,
     name VARCHAR,
     order_id INT,
     quantity INT,
     time TIMESTAMP
);