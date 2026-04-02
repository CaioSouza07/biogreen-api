package com.api.biogreen.domain.solicitacao;

import com.api.biogreen.domain.usuario.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class SolicitacaoService {

    private final SolicitacaoRepository repository;

    public void cadastrar(/*DadosCadastroSolicitacaoDTO dados, MultipartFile foto, */Authentication autenticado){
        var usuario = (Usuario) autenticado.getPrincipal();
        System.out.println(usuario);
    }

}
