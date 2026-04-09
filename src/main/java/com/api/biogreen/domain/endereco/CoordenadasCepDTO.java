package com.api.biogreen.domain.endereco;

import lombok.ToString;
import lombok.Value;

@Value
@ToString
public class CoordenadasCepDTO {
    double latitude;
    double longitude;
}
