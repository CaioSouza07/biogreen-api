package com.api.biogreen.infra.exception;

public class CepNaoExistenteException extends RuntimeException {
    public CepNaoExistenteException(String message) {
        super(message);
    }
}
