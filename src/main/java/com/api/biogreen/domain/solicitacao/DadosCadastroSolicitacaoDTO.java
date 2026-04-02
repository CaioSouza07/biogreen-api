package com.api.biogreen.domain.solicitacao;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public final class DadosCadastroSolicitacaoDTO {

    @NotBlank
    private final String descricao;

    @NotNull
    private final SolicitacaoStatus status;

}
