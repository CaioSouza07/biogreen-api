package com.api.biogreen.domain.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public final class DadosLoginUsuarioDTO {

    @NotBlank
    @Email
    private final String email;

    @NotBlank
    private final String senha;

}
