package com.api.biogreen.domain.solicitacao;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class DadosAtualizarSolicitacaoDTO {

    @NotNull
    Long id;

    String descricao;
    SolicitacaoStatus status;

}
