package com.api.biogreen.infra.security;

import com.api.biogreen.domain.usuario.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository repository;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid DadosLoginUsuarioDTO dados){
        var usernamePassword = new UsernamePasswordAuthenticationToken(dados.getEmail(), dados.getSenha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new TokenResponseDTO(token));
    }

    @PostMapping("/cadastro")
    public ResponseEntity cadastro(@RequestBody @Valid DadosCadastroUsuarioDTO dados){
        if (this.repository.findByEmail(dados.getEmail()) != null) return ResponseEntity.badRequest().build();

        String senhaEncriptografada = new BCryptPasswordEncoder().encode(dados.getSenha());
        Usuario usuario = new Usuario(dados, senhaEncriptografada);

        this.repository.save(usuario);

        return ResponseEntity.ok().build();
    }

}
