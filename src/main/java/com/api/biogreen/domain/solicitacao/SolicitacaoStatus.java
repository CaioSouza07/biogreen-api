package com.api.biogreen.domain.solicitacao;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SolicitacaoStatus {

    PENDENTE("pendente"),
    EM_ANDAMENTO("em_andamento"),
    CONCLUIDO("concluido");

    private String status;

}
