package com.api.biogreen;

import com.api.biogreen.infra.client.nominatim.NominatimClient;
import com.api.biogreen.infra.client.nominatim.NominatimResponseDTO;
import com.api.biogreen.infra.client.viacep.ViaCepClient;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class BiogreenApplicationTests {

	private final ViaCepClient viaCepClient = new ViaCepClient(new RestTemplate());
	private final NominatimClient nominatimClient = new NominatimClient(new RestTemplate());

	@Test
	void contextLoads() {
		var dados = viaCepClient.buscarPorCep("89225-560", 2654);

		System.out.println(nominatimClient.geocodificar(dados));
	}

}
