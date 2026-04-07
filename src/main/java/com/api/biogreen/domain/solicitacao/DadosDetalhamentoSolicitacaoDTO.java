package com.api.biogreen.domain.solicitacao;

import com.api.biogreen.domain.usuario.DadosDetalhamentoUsuarioDTO;
import lombok.Value;

import java.time.LocalDate;

@Value
public class DadosDetalhamentoSolicitacaoDTO {

    Long id;
    String descricao;
    String fotoUrl;
    SolicitacaoStatus status;
    LocalDate data;
    DadosDetalhamentoUsuarioDTO usuario;

    public DadosDetalhamentoSolicitacaoDTO(Solicitacao solicitacao){
        this.id = solicitacao.getId();
        this.descricao = solicitacao.getDescricao();
        this.fotoUrl = solicitacao.getFotoUrl();
        this.status = solicitacao.getStatus();
        this.usuario = new DadosDetalhamentoUsuarioDTO(solicitacao.getSolicitante());
        this.data = solicitacao.getData();
    }


}
