CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    version INT DEFAULT 0 NOT NULL,
    name VARCHAR(100) DEFAULT '' NOT NULL,
    brand VARCHAR(100) DEFAULT '' NOT NULL,
    description VARCHAR(500),
    color VARCHAR(32),
    material VARCHAR(64),
    length_mm INT DEFAULT 0 NOT NULL,
    width_mm INT DEFAULT 0 NOT NULL,
    height_mm INT DEFAULT 0 NOT NULL,
    weight_grams INT DEFAULT 0 NOT NULL,
    prize_euro DECIMAL(10, 2) DEFAULT 0 NOT NULL
);

CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    version INT DEFAULT 0 NOT NULL,
    product_id INT NOT NULL,
    username VARCHAR(64) NOT NULL,
    amount INT,
    creation_date ${dateDatatype},
    status VARCHAR(32) DEFAULT 'UNKNOWN' NOT NULL,
    recipient_name VARCHAR(100),
    recipient_address VARCHAR(200),
    CONSTRAINT fk_orders_product_id FOREIGN KEY (product_id) REFERENCES products (id)
);
