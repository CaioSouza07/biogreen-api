package com.api.biogreen.domain.local_descarte;

import lombok.Value;

@Value
public class DadosDetalhamentoLocalDescarteDTO {

    Long id;
    String nomeLocal;
    String cep;
    String logradouro;
    int numero;
    String complemento;
    String bairro;
    String cidade;
    String estado;
    double latitude;
    double longitude;

    public DadosDetalhamentoLocalDescarteDTO(LocalDescarte localDescarte){
        this.id = localDescarte.getId();
        this.nomeLocal = localDescarte.getNomeLocal();
        this.cep = localDescarte.getCep();
        this.logradouro = localDescarte.getLogradouro();
        this.numero = localDescarte.getNumero();
        this.complemento = localDescarte.getComplemento();
        this.bairro = localDescarte.getBairro();
        this.cidade = localDescarte.getCidade();
        this.estado = localDescarte.getEstado();
        this.latitude = localDescarte.getLatitude();
        this.longitude = localDescarte.getLongitude();
    }
}
