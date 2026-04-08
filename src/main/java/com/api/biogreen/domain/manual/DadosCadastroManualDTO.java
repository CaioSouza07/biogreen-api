package com.api.biogreen.domain.manual;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public final class DadosCadastroManualDTO {
    private String titulo;

    @JsonCreator
    public DadosCadastroManualDTO(
            @JsonProperty("titulo") String titulo
    ) {
        this.titulo = titulo;
    }
}
