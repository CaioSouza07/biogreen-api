package com.api.biogreen.domain.solicitacao;

import com.api.biogreen.domain.usuario.DadosDetalhamentoUsuarioDTO;
import com.api.biogreen.domain.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class DadosDetalhamentoSolicitacaoDTO {

    private final Long id;
    private final String descricao;
    private final String fotoUrl;
    private final SolicitacaoStatus status;
    private final DadosDetalhamentoUsuarioDTO usuario;

    public DadosDetalhamentoSolicitacaoDTO(Solicitacao solicitacao){
        this.id = solicitacao.getId();
        this.descricao = solicitacao.getDescricao();
        this.fotoUrl = solicitacao.getFotoUrl();
        this.status = solicitacao.getStatus();
        this.usuario = new DadosDetalhamentoUsuarioDTO(solicitacao.getSolicitante());
    }


}
