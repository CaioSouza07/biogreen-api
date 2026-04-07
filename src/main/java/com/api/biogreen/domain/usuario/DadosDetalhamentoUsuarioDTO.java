package com.api.biogreen.domain.usuario;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
public class DadosDetalhamentoUsuarioDTO {

    @NotNull
    Long id;

    @NotBlank
    String nome;

    @NotBlank
    String email;

    public DadosDetalhamentoUsuarioDTO(Usuario usuario){
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
    }

}
