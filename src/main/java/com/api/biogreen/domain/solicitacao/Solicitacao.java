package com.api.biogreen.domain.solicitacao;

import com.api.biogreen.domain.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;

@Entity
@Table(name = "solicitacoes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false, name = "foto_url")
    private String fotoUrl;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SolicitacaoStatus status;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario solicitante;

    public Solicitacao(@Valid DadosCadastroSolicitacaoDTO dados, String fotoUrl, Usuario usuario) {
        this.descricao = dados.getDescricao();
        this.fotoUrl = fotoUrl;
        this.status = dados.getStatus();
        this.solicitante = usuario;
    }
}
