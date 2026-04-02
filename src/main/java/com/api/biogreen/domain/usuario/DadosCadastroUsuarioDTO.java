package com.api.biogreen.domain.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public final class DadosCadastroUsuarioDTO {

    @NotBlank
    private final String nome;


    @NotBlank
    @Email
    private final String email;

    @NotBlank
    private final String senha;

    @NotNull
    private final UsuarioRole role;

}
