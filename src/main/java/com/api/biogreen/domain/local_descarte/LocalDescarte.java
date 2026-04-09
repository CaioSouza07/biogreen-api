package com.api.biogreen.domain.local_descarte;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;

@Entity
@Table(name = "locais_descarte")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LocalDescarte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "nome_local")
    private String nomeLocal;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false)
    private int numero;

    private String complemento;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    public LocalDescarte(@Valid DadosCadastroLocalDescarteDTO dados, String logradouro, String bairro, String cidade, String estado, double latitude, double longitude) {
        this.nomeLocal = dados.getNomeLocal();
        this.cep = dados.getCep();
        this.logradouro = logradouro;
        this.numero = dados.getNumero();
        this.complemento = dados.getComplemento();
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
