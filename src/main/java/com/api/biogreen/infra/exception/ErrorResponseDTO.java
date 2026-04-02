package com.api.biogreen.infra.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public final class ErrorResponseDTO {
    private final String message;
    private final Integer status;
    private final List<ErrorValidationResponseDTO> details;
}
