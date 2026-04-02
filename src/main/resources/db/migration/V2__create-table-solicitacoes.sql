CREATE TABLE solicitacoes(
    id BIGSERIAL PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    foto_url VARCHAR(255) NOT NULL UNIQUE,
    status VARCHAR(50)  NOT NULL,
    usuario_id BIGINT,
    CONSTRAINT fk_solicitacao_usuario
        FOREIGN KEY (usuario_id)
            REFERENCES usuarios (id)
);
