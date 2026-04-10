package com.api.biogreen.controller;

import com.api.biogreen.domain.local_descarte.DadosCadastroLocalDescarteDTO;
import com.api.biogreen.domain.local_descarte.DadosDetalhamentoLocalDescarteDTO;
import com.api.biogreen.domain.local_descarte.LocalDescarteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
