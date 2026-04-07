package com.api.biogreen.domain.usuario;


import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
public class DadosCadastroUsuarioDTO {

    @NotBlank
    String nome;


    @NotBlank
    @Email
    String email;

    @NotBlank
    String senha;

    @NotNull
    UsuarioRole role;

}
