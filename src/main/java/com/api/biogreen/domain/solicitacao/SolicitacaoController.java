package com.api.biogreen.domain.solicitacao;

import com.api.biogreen.infra.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/solicitacao")
@RequiredArgsConstructor
public class SolicitacaoController {

    private final SolicitacaoService solicitacaoService;

    @PostMapping(consumes = )
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroSolicitacaoDTO dados, @RequestParam("file") MultipartFile foto, Authentication autenticado){

        if (foto.isEmpty()) throw new BadRequestException("É necessário adicionar uma foto para cadastrar");

        solicitacaoService.cadastrar(autenticado);
        return ResponseEntity.ok().build();
    }

}
