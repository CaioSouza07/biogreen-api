package com.api.biogreen.domain.coleta;

import com.api.biogreen.domain.solicitacao.DadosCadastroSolicitacaoDTO;
import com.api.biogreen.domain.solicitacao.Solicitacao;
import com.api.biogreen.domain.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Clock;

@Entity
@Table(name = "coletas")
@NoArgsConstructor
public class Coleta extends Solicitacao {

    public Coleta(DadosCadastroSolicitacaoDTO dados, String caminhoExportar, Usuario principal, Clock clock) {
        super(dados, caminhoExportar, principal, clock);
    }
}
