package com.api.biogreen.domain.local_descarte;

import com.api.biogreen.infra.client.nominatim.NominatimClient;
import com.api.biogreen.infra.client.viacep.ViaCepClient;
import com.api.biogreen.infra.exception.CepNaoExistenteException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LocalDescarteService {

    private final LocalDescarteRepository repository;
    private final ViaCepClient viaCepClient;
    private final NominatimClient nominatimClient;

    @Transactional
    public DadosDetalhamentoLocalDescarteDTO cadastrar(DadosCadastroLocalDescarteDTO dados){
        var enderecoCep = viaCepClient.buscarPorCep(dados.getCep(), dados.getNumero());
        if (enderecoCep.getLogradouro() == null) throw new CepNaoExistenteException("Não existe um endereço com esse CEP");
        var coordenadas = nominatimClient.geocodificar(enderecoCep);
        var localDescarte = new LocalDescarte(dados, enderecoCep, coordenadas);
        repository.save(localDescarte);
        return new DadosDetalhamentoLocalDescarteDTO(localDescarte);
    }
}
