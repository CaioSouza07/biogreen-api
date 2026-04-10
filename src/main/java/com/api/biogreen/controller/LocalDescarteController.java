package com.api.biogreen.controller;

import com.api.biogreen.domain.local_descarte.DadosCadastroLocalDescarteDTO;
import com.api.biogreen.domain.local_descarte.DadosDetalhamentoLocalDescarteDTO;
import com.api.biogreen.domain.local_descarte.LocalDescarteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/localdescarte")
@RequiredArgsConstructor
public class LocalDescarteController {

    private final LocalDescarteService service;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoLocalDescarteDTO> cadastrar(
            @RequestBody @Valid DadosCadastroLocalDescarteDTO dados,
            UriComponentsBuilder uriBuilder){
        var localDescarte = service.cadastrar(dados);
        var uri = uriBuilder.path("localdescarte/{id}").buildAndExpand(localDescarte.getId()).toUri();
        return ResponseEntity.created(uri).body(localDescarte);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoLocalDescarteDTO> detalhar(@PathVariable Long id){
        var local = service.detalhar(id);
        return ResponseEntity.ok(local);
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoLocalDescarteDTO>> listarLocaisDescarte(@PageableDefault(size = 10)Pageable paginacao){
        var page = service.listarLocalDescarte(paginacao);
        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
