package com.gestaobusiness.controleestoque.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = Venda.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
public class Venda {

    public static final String TABLE_NAME = "Vendas";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne
    private Cliente cliente;

}
