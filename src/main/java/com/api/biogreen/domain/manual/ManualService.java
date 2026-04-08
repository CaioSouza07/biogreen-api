package com.api.biogreen.domain.manual;

import com.api.biogreen.domain.usuario.Usuario;
import com.api.biogreen.infra.exception.BadRequestException;
import com.api.biogreen.infra.exception.NotFoundException;
import com.api.biogreen.infra.files.FilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.Clock;

@Service
@RequiredArgsConstructor
public class ManualService {

    private final ManualRepository repository;
    private final FilesService filesService;

    @Value("${api.upload-dir.manuais}")
    private String uploadDir;


    @Transactional
    public DadosDetalhamentoManualDTO cadastrar(DadosCadastroManualDTO dados, MultipartFile pdf, Usuario usuario) {

        if (pdf.isEmpty()) throw new BadRequestException("É necessário adicionar um PDF para cadastrar");
        validarPdf(pdf);

        var caminhoExportar = filesService.salvar(uploadDir, pdf);
        var manual = new Manual(dados, caminhoExportar, usuario, Clock.systemDefaultZone());
        repository.save(manual);

        return new DadosDetalhamentoManualDTO(manual);
    }

    @Transactional
    public DadosDetalhamentoManualDTO atualizar(DadosAtualizarManualDTO dados, MultipartFile pdf) {
        validarPdf(pdf);

        Manual manual = repository.findById(dados.getId())
                .orElseThrow(() -> new NotFoundException("Manual não encontrado"));

        if (!pdf.isEmpty()) filesService.atualizar(manual.getManualUrl(), pdf);

        manual.atualizarInformacoes(dados);
        return new DadosDetalhamentoManualDTO(manual);
    }

    public DadosDetalhamentoManualDTO detalhar(Long id) {
        var manual = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Manual não encontrado"));
        return new DadosDetalhamentoManualDTO(manual);
    }

    public Page<DadosDetalhamentoManualDTO> listarManuais(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosDetalhamentoManualDTO::new);
    }

    @Transactional
    public void deletar(Long id){
        var manual = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Manual não encontrado"));
        filesService.deletar(manual.getManualUrl());
        repository.delete(manual);
    }

    private void validarPdf(MultipartFile pdf){
        if (!pdf.isEmpty()) {
            if (!MediaType.APPLICATION_PDF_VALUE.equals(pdf.getContentType())) {
                throw new BadRequestException("Arquivo deve ser um PDF");
            }
        }
    }
}
