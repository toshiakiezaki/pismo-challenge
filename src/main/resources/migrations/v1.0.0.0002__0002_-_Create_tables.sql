CREATE TABLE account (
    id BIGSERIAL NOT NULL,
    document_type DOCUMENT_TYPE NOT NULL,
    document_number VARCHAR(14),
    CONSTRAINT pk_account PRIMARY KEY(id)
);

CREATE TABLE operation_type (
    id BIGINT NOT NULL,
    description VARCHAR(120) NOT NULL,
    entry OPERATION_ENTRY NOT NULL,
    CONSTRAINT pk_operation_type PRIMARY KEY(id)
);

CREATE TABLE transaction (
    id BIGSERIAL NOT NULL,
    account_id BIGINT NOT NULL,
    operation_type_id BIGINT NOT NULL,
    amount NUMERIC(19,2),
    event_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_transaction PRIMARY KEY (id),
    CONSTRAINT fk_transaction_001 FOREIGN KEY (account_id) REFERENCES account (id)
        ON UPDATE RESTRICT ON DELETE RESTRICT,
    CONSTRAINT fk_transaction_002 FOREIGN KEY (operation_type_id) REFERENCES operation_type (id)
        ON UPDATE RESTRICT ON DELETE RESTRICT
);