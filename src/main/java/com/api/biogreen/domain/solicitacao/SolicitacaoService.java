package com.api.biogreen.domain.solicitacao;

import com.api.biogreen.domain.usuario.Usuario;
import com.api.biogreen.infra.exception.UsuarioNaoPermitidoException;
import com.api.biogreen.infra.exception.BadRequestException;
import com.api.biogreen.infra.exception.UploadImagemException;
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

    @Value("${api.upload-dir.solicitacoes}")
    private String uploadDir;


    @Transactional
    public Solicitacao cadastrar(DadosCadastroSolicitacaoDTO dados, MultipartFile foto, Authentication autenticado){

        Path diretorio = Paths.get(uploadDir);
        Path caminhoExportar = diretorio.resolve(UUID.randomUUID() + TratadorArquivo.obterExtensao(foto.getOriginalFilename()));

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

    public Solicitacao atualizar(DadosAtualizarSolicitacaoDTO dados, MultipartFile foto, Authentication authentication){

        var solicitacao = repository.findById(dados.getId());
        var usuarioLogado = (Usuario) authentication.getPrincipal();
        if (solicitacao.isEmpty()) throw new BadRequestException("Não existe nenhuma solicitação com esse ID");
        if(!solicitacao.get().getSolicitante().equals(usuarioLogado)) throw new UsuarioNaoPermitidoException("Apenas o usuário que cadastrou a solicitação pode atualiza-la");

        Path diretorio = Paths.get(uploadDir);
        Path caminhoExportar = diretorio.resolve(UUID.randomUUID() + TratadorArquivo.obterExtensao(foto.getOriginalFilename()));




    }

    public Solicitacao detalhar(Long id) {
        Optional<Solicitacao> solicitacao = repository.findById(id);
        if (solicitacao.isEmpty()) throw new BadRequestException("Não existe nenhuma solicitação com esse id");
        return solicitacao.get();
    }

    @Transactional
    public void deletar(Long id, Authentication authentication) {
        var solicitacao = repository.findById(id);
        if (solicitacao.isEmpty()) throw new BadRequestException("Não existe nenhuma solicitação com esse id");

        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();
        if(!solicitacao.get().getSolicitante().equals(usuarioLogado)) throw new UsuarioNaoPermitidoException("Apenas o usuário que cadastrou a solicitação pode remove-la");

        Path diretorio = Paths.get(solicitacao.get().getFotoUrl());

        try {
            Files.deleteIfExists(diretorio);
        } catch (IOException e) {
            throw new UploadImagemException("Erro interno ao deletar a imagem da foto");
        }

        repository.delete(solicitacao.get());

    }

    @Transactional
    public void deletarAdmin(Long id) {
        Optional<Solicitacao> solicitacao = repository.findById(id);
        if (solicitacao.isEmpty()) throw new BadRequestException("Não existe nenhuma solicitação com esse id");

        Path diretorio = Paths.get(solicitacao.get().getFotoUrl());

        try {
            System.out.println(diretorio.resolve(solicitacao.get().getFotoUrl()));
            Files.deleteIfExists(diretorio);

        } catch (IOException e) {
            throw new UploadImagemException("Erro interno ao deletar a imagem da foto");
        }

        repository.delete(solicitacao.get());

    }

    public Page<DadosDetalhamentoSolicitacaoDTO> listarSolicitacoes(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosDetalhamentoSolicitacaoDTO::new);
//        mesma coisa que o de baixo mas em metodo de referencia
//        return repository.findAll(paginacao).map(p -> new DadosDetalhamentoSolicitacaoDTO(p));
    }


}
