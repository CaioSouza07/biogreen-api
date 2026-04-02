package com.api.biogreen.domain.solicitacao;

import com.api.biogreen.domain.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    @Column(nullable = false)
    private String fotoUrl;

    @Enumerated(EnumType.STRING)
    private SolicitacaoStatus status;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario solicitante;

}
