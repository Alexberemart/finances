ALTER TABLE import_financial_movements
    ADD COLUMN category_id VARCHAR(255);

ALTER TABLE import_financial_movements
    ADD CONSTRAINT fk_import_financial_movements_category
    FOREIGN KEY (category_id)
    REFERENCES financial_movement_category(id)
    ON DELETE SET NULL
    ON UPDATE CASCADE;