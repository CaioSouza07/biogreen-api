package com.api.biogreen.infra.client.nominatim;

import com.api.biogreen.domain.endereco.CoordenadasCepDTO;
import com.api.biogreen.domain.endereco.EnderecoCepDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class NominatimClient {

    private static final String USER_AGENT = "biogreen-api/1.0 (caiodesouza.cds@gmail.com)";
    private final RestTemplate restTemplate;

    public CoordenadasCepDTO geocodificar(EnderecoCepDTO dados) {

        var url = UriComponentsBuilder
                .fromHttpUrl("https://nominatim.openstreetmap.org/search")
                .queryParam("street", dados.getLogradouro() + " " + dados.getNumero())
                .queryParam("city", dados.getCidade())
                .queryParam("state", dados.getEstado())
                .queryParam("country", "Brazil")
                .queryParam("format", "json")
                .queryParam("limit", 1)
                .queryParam("countrycodes", "br")
                .build()
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", USER_AGENT);
        headers.set("Accept", "application/json");

        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<NominatimResponseDTO[]> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        entity,
                        NominatimResponseDTO[].class
                );

        NominatimResponseDTO[] body = response.getBody();

        if (body == null || body.length == 0) {
            return null;
        }
        return new CoordenadasCepDTO(Double.parseDouble(body[0].getLat()), Double.parseDouble(body[0].getLon()));
    }
}