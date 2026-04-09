package com.api.biogreen.domain.endereco;

import lombok.ToString;
import lombok.Value;

@Value
@ToString
public class EnderecoCepDTO {
    String cep;
    String logradouro;
    Integer numero;
    String bairro;
    String cidade;
    String estado;
    String uf;
}
