package com.gestaobusiness.controleestoque.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = Venda.TABLE_NAME)
public class Venda {

    public static final String TABLE_NAME = "Vendas";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Produto> produtos;

}
