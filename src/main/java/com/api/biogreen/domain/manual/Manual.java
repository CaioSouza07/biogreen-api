package com.api.biogreen.domain.manual;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @Column(nullable = false)
    private String manualUrl;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private LocalDate data;

}
