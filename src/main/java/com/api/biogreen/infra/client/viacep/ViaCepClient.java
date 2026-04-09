package com.api.biogreen.infra.client.viacep;

import com.api.biogreen.domain.endereco.EnderecoCepDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class ViaCepClient {

    private final RestTemplate restTemplate;

    public EnderecoCepDTO buscarPorCep(String cep, int numero){

        var url = UriComponentsBuilder
                .fromHttpUrl("https://viacep.com.br")
                .pathSegment("ws", cep, "json")
                .build()
                .toString();

        ViaCepResponseDTO response = restTemplate.getForObject(url, ViaCepResponseDTO.class);

        if (response != null) {
            return new EnderecoCepDTO(
                response.getCep(),
                response.getLogradouro(),
                numero,
                response.getBairro(),
                response.getLocalidade(),
                response.getEstado(),
                response.getUf()
            );
        }
        return null;
    }
}
