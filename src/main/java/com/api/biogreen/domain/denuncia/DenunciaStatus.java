package com.api.biogreen.domain.denuncia;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DenunciaStatus {

    PENDENTE("pendente"),
    EM_ANDAMENTO("em_andamento"),
    CONCLUIDO("concluido");

    private String status;

}


