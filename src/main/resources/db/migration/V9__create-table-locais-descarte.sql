CREATE TABLE locais_descarte(
    id BIGSERIAL PRIMARY KEY,
    nome_local VARCHAR(50) NOT NULL,
    cep VARCHAR(6) NOT NULL UNIQUE,
    logradouro VARCHAR(50) NOT NULL,
    numero INTEGER NOT NULL,
    complemento VARCHAR(100),
    bairro VARCHAR(20) NOT NULL,
    cidade VARCHAR(20) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    latitude DOUBLE PRECISION NOT NULL,
    longitude DOUBLE PRECISION NOT NULL
);