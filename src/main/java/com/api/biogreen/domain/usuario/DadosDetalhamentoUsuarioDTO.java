package com.api.biogreen.domain.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public final class DadosDetalhamentoUsuarioDTO {

    @NotNull
    private final Long id;

    @NotBlank
    private final String nome;

    @NotBlank
    private final String email;

    public DadosDetalhamentoUsuarioDTO(Usuario usuario){
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
    }

}
