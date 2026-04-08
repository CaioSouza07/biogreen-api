package com.api.biogreen.domain.manual;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class DadosAtualizarManualDTO {
    @NotNull
    Long id;

    String titulo;
}
