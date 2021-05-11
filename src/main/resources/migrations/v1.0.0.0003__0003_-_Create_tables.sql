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
