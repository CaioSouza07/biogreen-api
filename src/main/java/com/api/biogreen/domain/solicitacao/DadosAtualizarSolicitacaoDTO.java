package com.api.biogreen.domain.solicitacao;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public final class DadosAtualizarSolicitacaoDTO {

    @NotNull
    private final Long id;

    private final String descricao;

    private final SolicitacaoStatus status;

}
