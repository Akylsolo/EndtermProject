CREATE TABLE IF NOT EXISTS menu_item (
                                         id SERIAL PRIMARY KEY,
                                         type VARCHAR(20) NOT NULL,
    name VARCHAR(100) NOT NULL,
    price NUMERIC(10,2) NOT NULL
    );

CREATE TABLE IF NOT EXISTS orders (
                                      id SERIAL PRIMARY KEY,
                                      customer_name VARCHAR(100) NOT NULL,
    status VARCHAR(30) NOT NULL,
    total_price NUMERIC(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS order_item (
                                          order_id INT NOT NULL,
                                          menu_item_id INT NOT NULL,
                                          quantity INT NOT NULL,
                                          item_price NUMERIC(10,2) NOT NULL,
    PRIMARY KEY (order_id, menu_item_id),
    CONSTRAINT fk_order
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
    CONSTRAINT fk_menu_item
    FOREIGN KEY (menu_item_id) REFERENCES menu_item(id)
    );
