package com.api.biogreen.infra.exception;

public class UsuarioNaoPermitidoException extends RuntimeException {
    public UsuarioNaoPermitidoException(String message) {
        super(message);
    }
}
