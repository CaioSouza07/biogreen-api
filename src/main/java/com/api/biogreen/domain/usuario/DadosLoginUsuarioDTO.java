package com.api.biogreen.domain.usuario;

import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Value
public class DadosLoginUsuarioDTO {

    @NotBlank
    @Email
    String email;

    @NotBlank
    String senha;

}
