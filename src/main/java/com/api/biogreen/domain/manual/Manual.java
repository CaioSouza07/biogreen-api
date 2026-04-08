package com.api.biogreen.domain.manual;

import com.api.biogreen.domain.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.Clock;
import java.time.LocalDate;

@Entity
@Table(name = "manuais")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Manual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "manual_url")
    private String manualUrl;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "responsavel_id")
    private Usuario responsavel;

    public Manual(@Valid DadosCadastroManualDTO dados, String caminhoExportar, Usuario usuario, Clock clock) {
        this.manualUrl = caminhoExportar;
        this.titulo = dados.getTitulo();
        this.data = LocalDate.now();
        this.responsavel = usuario;
    }

    public void atualizarInformacoes(DadosAtualizarManualDTO dados) {
        if (dados.getTitulo() != null) this.titulo = dados.getTitulo();
    }
}
