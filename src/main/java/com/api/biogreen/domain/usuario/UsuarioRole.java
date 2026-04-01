package com.api.biogreen.domain.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UsuarioRole {

    ADMIN("admin"),
    USER("user");

    private final String role;

}
