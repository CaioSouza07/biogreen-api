package com.api.biogreen.domain.denuncia;

import com.api.biogreen.domain.usuario.Usuario;
import com.api.biogreen.infra.exception.UsuarioNaoPermitidoException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.Clock;
import java.time.LocalDate;

@Entity
@Table(name = "denuncias")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Denuncia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false, name = "foto_url")
    private String fotoUrl;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DenunciaStatus status;

    @Column(nullable = false)
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario denunciante;

    public Denuncia(@Valid DadosCadastroDenunciaDTO dados, String fotoUrl, Usuario usuario, Clock clock){
        this.descricao = dados.getDescricao();
        this.fotoUrl = fotoUrl;
        this.status = DenunciaStatus.PENDENTE;
        this.data = LocalDate.now(clock);
        this.denunciante = usuario;
    }

    public void validarPermissaoRemocao(Usuario usuario){
        if (usuario.isAdmin()){
            return;
        }

        if (!this.denunciante.equals(usuario)){
            throw new UsuarioNaoPermitidoException("Apenas o autor pode efetuar alterações ou remoção nessa denuncia");
        }
    }

    // aqui preciso implementar o metodo de atualizar as informacoes ainda

}
