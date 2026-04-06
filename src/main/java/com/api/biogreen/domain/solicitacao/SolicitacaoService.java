package com.api.biogreen.domain.solicitacao;

import com.api.biogreen.domain.usuario.Usuario;
import com.api.biogreen.infra.exception.UploadImagemException;
import com.api.biogreen.utils.TratadorArquivo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SolicitacaoService {

    private final SolicitacaoRepository repository;

    @Value("${api.upload-dir.solicitacoes}")
    private String uploadDir;

    public Solicitacao cadastrar(DadosCadastroSolicitacaoDTO dados, MultipartFile foto, Authentication autenticado){

        Path diretorio = Paths.get(uploadDir);

        Path caminhoExportar = diretorio.resolve(
                UUID.randomUUID() + TratadorArquivo.obterExtensao(foto.getOriginalFilename())
        );

        try {
            foto.transferTo(caminhoExportar);
        } catch (IOException e) {
            throw new UploadImagemException("Erro interno ao salvar a imagem da solicitação no diretório");
        }

        var usuario = (Usuario) autenticado.getPrincipal();
        var solicitacao = new Solicitacao(dados, caminhoExportar.toString(), usuario);

        repository.save(solicitacao);

        return solicitacao;

    }





}
