package com.api.biogreen.domain.denuncia;

import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class DadosCadastroDenunciaDTO {
    @NotBlank
    String descricao;
}
