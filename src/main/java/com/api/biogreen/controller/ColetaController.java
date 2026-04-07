package com.api.biogreen.controller;

import com.api.biogreen.domain.coleta.ColetaService;
import com.api.biogreen.domain.solicitacao.DadosAtualizarSolicitacaoDTO;
import com.api.biogreen.domain.solicitacao.DadosCadastroSolicitacaoDTO;
import com.api.biogreen.domain.solicitacao.DadosDetalhamentoSolicitacaoDTO;
import com.api.biogreen.infra.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/coleta")
@RequiredArgsConstructor
public class ColetaController {

    private final ColetaService coletaService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity cadastrar(
            @RequestPart("dados") @Valid DadosCadastroSolicitacaoDTO dados,
            @RequestParam("file") MultipartFile foto,
            Authentication autenticado,
            UriComponentsBuilder uriBuilder
    ){
        if (foto.isEmpty()) throw new BadRequestException("É necessário adicionar uma foto para cadastrar");
        if (!foto.getContentType().startsWith("image")) throw new BadRequestException("Arquivo deve ser uma imagem");

        var solicitacao = coletaService.cadastrar(dados, foto, autenticado);

        var uri = uriBuilder.path("coleta/{id}").buildAndExpand(solicitacao.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoSolicitacaoDTO(solicitacao));
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity atualizar(
            @RequestPart("dados") @Valid DadosAtualizarSolicitacaoDTO dados,
            @RequestParam("file") MultipartFile foto,
            Authentication authentication
    ){
        if (!foto.isEmpty()) {
            if (!foto.getContentType().startsWith("image")) {
                throw new BadRequestException("Arquivo deve ser uma imagem");
            }
        }

        var solicitacao = coletaService.atualizar(dados, foto, authentication);

        return ResponseEntity.ok(new DadosDetalhamentoSolicitacaoDTO(solicitacao));
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoSolicitacaoDTO>> listarSolicitacoes(@PageableDefault(size = 10) Pageable paginacao){
        var page = coletaService.listarSolicitacoes(paginacao);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var solicitacao = coletaService.detalhar(id);
        return ResponseEntity.ok(new DadosDetalhamentoSolicitacaoDTO(solicitacao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id, Authentication authentication){
        coletaService.deletar(id, authentication);
        return ResponseEntity.noContent().build();
    }

}
