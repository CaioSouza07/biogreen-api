package com.api.biogreen.domain.solicitacao;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Getter
public final class DadosCadastroSolicitacaoDTO {

    @NotBlank
    private final String descricao;

    // como esse meu dto tem apenas 1 atributo, o Jackson nao sabe se interpreta como json ou texto simples,
    // entao precisa do metodo abaixo pra deixar claro que é em json
    @JsonCreator
    public DadosCadastroSolicitacaoDTO(
            @JsonProperty("descricao") String descricao
    ) {
        this.descricao = descricao;
    }


}
