package com.api.biogreen.controller;

import com.api.biogreen.domain.manual.DadosAtualizarManualDTO;
import com.api.biogreen.domain.manual.DadosCadastroManualDTO;
import com.api.biogreen.domain.manual.DadosDetalhamentoManualDTO;
import com.api.biogreen.domain.manual.ManualService;
import com.api.biogreen.domain.usuario.Usuario;
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
@RequestMapping("/manual")
@RequiredArgsConstructor
public class ManualController {

    private final ManualService service;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DadosDetalhamentoManualDTO> cadastrar(
            @RequestPart("dados") @Valid DadosCadastroManualDTO dados,
            @RequestPart("file")MultipartFile pdf,
            Authentication autenticado,
            UriComponentsBuilder uriBuilder
            ){
        var manual = service.cadastrar(dados, pdf, (Usuario) autenticado.getPrincipal());
        var uri = uriBuilder.path("manual/{id}").buildAndExpand(manual.getId()).toUri();

        return ResponseEntity.created(uri).body(manual);
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DadosDetalhamentoManualDTO> atualizar(
            @RequestPart("dados") @Valid DadosAtualizarManualDTO dados,
            @RequestPart("file") MultipartFile pdf
            ){
        var manual = service.atualizar(dados, pdf);
        return ResponseEntity.ok(manual);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoManualDTO> detalhar(@PathVariable Long id){
        var manual = service.detalhar(id);
        return ResponseEntity.ok(manual);
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoManualDTO>> listarManuais(@PageableDefault(size = 10) Pageable paginacao){
        var page = service.listarManuais(paginacao);
        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
