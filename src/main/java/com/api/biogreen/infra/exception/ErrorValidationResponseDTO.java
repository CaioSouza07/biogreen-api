package com.api.biogreen.infra.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.FieldError;

@AllArgsConstructor
@Getter
public final class ErrorValidationResponseDTO {

    private final String campo;
    private final String mensagem;

    public ErrorValidationResponseDTO(FieldError error){
        this(error.getField(), error.getDefaultMessage());
    }
}
