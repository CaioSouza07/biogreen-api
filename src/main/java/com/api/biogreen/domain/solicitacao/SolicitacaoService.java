package com.api.biogreen.domain.solicitacao;

import com.api.biogreen.domain.usuario.Usuario;
import com.api.biogreen.infra.exception.UsuarioNaoPermitidoException;
import com.api.biogreen.infra.exception.BadRequestException;
import com.api.biogreen.infra.exception.UploadImagemException;
import com.api.biogreen.infra.files.FilesService;
import com.api.biogreen.utils.TratadorArquivo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SolicitacaoService {

    private final SolicitacaoRepository repository;
    private final FilesService filesService;

    @Value("${api.upload-dir.solicitacoes}")
    private String uploadDir;


    @Transactional
    public Solicitacao cadastrar(DadosCadastroSolicitacaoDTO dados, MultipartFile foto, Authentication autenticado){

        var caminhoExportar = filesService.salvar(uploadDir, foto);
        var solicitacao = new Solicitacao(dados, caminhoExportar, (Usuario) autenticado.getPrincipal());

        repository.save(solicitacao);

        return solicitacao;
    }

    @Transactional
    public Solicitacao atualizar(DadosAtualizarSolicitacaoDTO dados, MultipartFile foto, Authentication authentication){

        Solicitacao solicitacao = repository.findById(dados.getId())
                .orElseThrow(() -> new BadRequestException("Solicitação não encontrada"));
        solicitacao.validarPermissaoRemocao((Usuario) authentication.getPrincipal());

        if (!foto.isEmpty()) filesService.atualizar(solicitacao.getFotoUrl(), foto);

        solicitacao.atualizarInformacoes(dados);

        return solicitacao;
    }

    @Transactional
    public void deletar(Long id, Authentication authentication) {

        Solicitacao solicitacao = repository.findById(id)
                .orElseThrow(() -> new BadRequestException("Solicitação não encontrada"));
        solicitacao.validarPermissaoRemocao((Usuario) authentication.getPrincipal());

        filesService.deletar(solicitacao.getFotoUrl());
        repository.delete(solicitacao);
    }

    @Transactional
    public void deletarAdmin(Long id) {

        Solicitacao solicitacao = repository.findById(id)
                .orElseThrow(() -> new BadRequestException("Solicitação não encontrada"));

        filesService.deletar(solicitacao.getFotoUrl());
        repository.delete(solicitacao);
    }

    public Solicitacao detalhar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BadRequestException("Solicitação não encontrada"));
    }

    public Page<DadosDetalhamentoSolicitacaoDTO> listarSolicitacoes(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosDetalhamentoSolicitacaoDTO::new);
    }
}
