package com.api.biogreen.domain.manual;

import lombok.Value;

import java.time.LocalDate;

@Value
public class DadosDetalhamentoManualDTO {

    Long id;
    String titulo;
    String manualUrl;
    LocalDate data;

    public DadosDetalhamentoManualDTO(Manual manual){
        this.id = manual.getId();
        this.titulo = manual.getTitulo();
        this.manualUrl = manual.getManualUrl();
        this.data = manual.getData();
    }
}
