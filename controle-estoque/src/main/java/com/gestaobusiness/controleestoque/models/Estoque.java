package com.gestaobusiness.controleestoque.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = Estoque.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
public class Estoque {

    public static final String TABLE_NAME = "Estoque";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Produto produto;

    private Integer quantidadeMinima;

    private Integer quantidadeDisponivel;

}
