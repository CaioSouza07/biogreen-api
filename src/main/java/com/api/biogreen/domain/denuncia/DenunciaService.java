package com.api.biogreen.domain.denuncia;

import com.api.biogreen.domain.solicitacao.DadosAtualizarSolicitacaoDTO;
import com.api.biogreen.domain.solicitacao.DadosCadastroSolicitacaoDTO;
import com.api.biogreen.domain.solicitacao.DadosDetalhamentoSolicitacaoDTO;
import com.api.biogreen.domain.usuario.Usuario;
import com.api.biogreen.infra.exception.NotFoundException;
import com.api.biogreen.infra.files.FilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.Clock;

@Service
@RequiredArgsConstructor
public class DenunciaService {

    private final DenunciaRepository repository;
    private final FilesService filesService;

    @Value("${api.upload-dir.solicitacoes}")
    private String uploadDir;


    @Transactional
    public DadosDetalhamentoSolicitacaoDTO cadastrar(DadosCadastroSolicitacaoDTO dados, MultipartFile foto, Usuario usuario){

        var caminhoExportar = filesService.salvar(uploadDir, foto);
        var solicitacao = new Denuncia(dados, caminhoExportar, usuario, Clock.systemDefaultZone());

        repository.save(solicitacao);

        return new DadosDetalhamentoSolicitacaoDTO(solicitacao);
    }

    @Transactional
    public DadosDetalhamentoSolicitacaoDTO atualizar(DadosAtualizarSolicitacaoDTO dados, MultipartFile foto, Usuario usuario){

        Denuncia solicitacao = repository.findById(dados.getId())
                .orElseThrow(() -> new NotFoundException("Denuncia não encontrada"));
        solicitacao.validarPermissaoRemocao(usuario);

        if (!foto.isEmpty()) filesService.atualizar(solicitacao.getFotoUrl(), foto);

        solicitacao.atualizarInformacoes(dados);

        return new DadosDetalhamentoSolicitacaoDTO(solicitacao);
    }

    @Transactional
    public void deletar(Long id, Usuario usuario) {

        Denuncia solicitacao = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Denuncia não encontrada"));
        solicitacao.validarPermissaoRemocao(usuario);

        filesService.deletar(solicitacao.getFotoUrl());
        repository.delete(solicitacao);
    }

    public DadosDetalhamentoSolicitacaoDTO detalhar(Long id) {
        var solicitacao = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Denuncia não encontrada"));
        return new DadosDetalhamentoSolicitacaoDTO(solicitacao);
    }

    public Page<DadosDetalhamentoSolicitacaoDTO> listarSolicitacoes(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosDetalhamentoSolicitacaoDTO::new);
    }
}
