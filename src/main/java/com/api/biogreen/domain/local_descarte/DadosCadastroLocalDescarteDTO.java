package com.api.biogreen.domain.local_descarte;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Value
public class DadosCadastroLocalDescarteDTO {

    @NotBlank
    String nomeLocal;

    @NotBlank
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP precisa seguir o padrão: XXXXX-XXX")
    String cep;

    @NotNull
    @Positive
    Integer numero;

    String complemento;
}
