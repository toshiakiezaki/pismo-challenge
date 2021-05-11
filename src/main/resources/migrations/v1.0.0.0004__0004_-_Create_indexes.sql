CREATE INDEX ix_account_001 ON account (document_type);
CREATE INDEX ix_account_002 ON account (document_number);

CREATE INDEX ix_transaction_001 ON transaction (account_id);
CREATE INDEX ix_transaction_002 ON transaction (operation_type_id);
