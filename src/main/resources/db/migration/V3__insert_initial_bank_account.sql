INSERT INTO bank_accounts (id, name) VALUES ('bbva', 'BBVA Account')
ON DUPLICATE KEY UPDATE name = VALUES(name);

-- For PostgreSQL use:
-- INSERT INTO bank_accounts (id, name) VALUES ('bbva', 'BBVA Account')
-- ON CONFLICT (id) DO NOTHING;

-- For H2 use:
-- MERGE INTO bank_accounts (id, name) KEY(id) VALUES ('bbva', 'BBVA Account');