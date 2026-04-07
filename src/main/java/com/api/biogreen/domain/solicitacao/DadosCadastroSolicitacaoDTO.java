package com.api.biogreen.domain.solicitacao;

import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class DadosCadastroSolicitacaoDTO {

    @NotBlank
    String descricao;

}
