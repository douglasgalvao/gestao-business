package com.gestaobusiness.controleestoque.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "nome", unique = true)
    private Long nome;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_categoria_produto_id")
    private CategoriaProduto categoriaProduto;

}
