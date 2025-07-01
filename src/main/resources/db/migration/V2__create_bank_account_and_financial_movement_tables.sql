CREATE TABLE bank_accounts (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255)
);

ALTER TABLE financial_movements
    ADD COLUMN bank_account_id VARCHAR(255) NOT NULL,
    ADD CONSTRAINT fk_financial_movement_bank_account
        FOREIGN KEY (bank_account_id)
        REFERENCES bank_accounts(id)
        ON DELETE RESTRICT;