package com.api.biogreen.controller;

import com.api.biogreen.domain.manual.DadosCadastroManualDTO;
import com.api.biogreen.domain.manual.DadosDetalhamentoManualDTO;
import com.api.biogreen.domain.manual.ManualService;
import com.api.biogreen.domain.usuario.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/manual")
@RequiredArgsConstructor
public class ManualController {

    private final ManualService service;

    @PostMapping
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
}
