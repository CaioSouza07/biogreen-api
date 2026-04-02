package com.api.biogreen.infra.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class TokenResponseDTO {
    private final String token;
}
