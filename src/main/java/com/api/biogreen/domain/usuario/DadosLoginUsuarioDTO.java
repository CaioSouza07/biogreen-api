package com.api.biogreen.domain.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class DadosLoginUsuarioDTO {

    private final String email;
    private final String senha;

}
