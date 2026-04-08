package com.api.biogreen.domain.manual;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public final class DadosCadastroManualDTO {

    @NotBlank
    private String titulo;

    @JsonCreator
    public DadosCadastroManualDTO(
            @JsonProperty("titulo") String titulo
    ) {
        this.titulo = titulo;
    }
}
