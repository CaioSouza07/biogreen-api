package com.api.biogreen.controller;

import com.api.biogreen.domain.solicitacao.DadosCadastroSolicitacaoDTO;
import com.api.biogreen.domain.solicitacao.DadosDetalhamentoSolicitacaoDTO;
import com.api.biogreen.domain.solicitacao.SolicitacaoService;
import com.api.biogreen.infra.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/solicitacao")
@RequiredArgsConstructor
public class SolicitacaoController {

    private final SolicitacaoService solicitacaoService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity cadastrar(
            @RequestPart("dados") @Valid DadosCadastroSolicitacaoDTO dados,
            @RequestParam("file") MultipartFile foto,
            Authentication autenticado,
            UriComponentsBuilder uriBuilder){

        if (foto.isEmpty()) throw new BadRequestException("É necessário adicionar uma foto para cadastrar");
        if (!foto.getContentType().startsWith("image")) throw new BadRequestException("Arquivo deve ser uma imagem");

        var solicitacao = solicitacaoService.cadastrar(dados, foto, autenticado);

        var uri = uriBuilder.path("solicitacao/{id}").buildAndExpand(solicitacao.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoSolicitacaoDTO(solicitacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var solicitacao = solicitacaoService.detalhar(id);

        return ResponseEntity.ok(new DadosDetalhamentoSolicitacaoDTO(solicitacao));
    }

}
