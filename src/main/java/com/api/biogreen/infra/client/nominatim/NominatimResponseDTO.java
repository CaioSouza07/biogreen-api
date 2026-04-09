package com.api.biogreen.infra.client.nominatim;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NominatimResponseDTO {
    private String lat;
    private String lon;
}
