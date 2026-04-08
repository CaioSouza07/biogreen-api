CREATE TABLE manuais(
    id BIGSERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    manual_url VARCHAR(255) NOT NULL UNIQUE,
    data DATE DEFAULT CURRENT_DATE,
    responsavel_id BIGINT,
    CONSTRAINT fk_manual_usuario
        FOREIGN KEY (responsavel_id)
            REFERENCES usuarios (id)
);
