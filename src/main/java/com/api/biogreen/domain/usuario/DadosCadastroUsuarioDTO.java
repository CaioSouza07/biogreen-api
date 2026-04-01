package com.api.biogreen.domain.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class DadosCadastroUsuarioDTO {

    private final String nome;
    private final String email;
    private final String senha;
    private final UsuarioRole role;

}
