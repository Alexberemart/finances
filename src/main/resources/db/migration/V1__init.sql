CREATE TABLE import_financial_movements (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    date DATE,
    description VARCHAR(255),
    amount DECIMAL(19,2),
    label VARCHAR(255)
);