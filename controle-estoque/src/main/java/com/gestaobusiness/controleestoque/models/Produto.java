package com.gestaobusiness.controleestoque.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = Produto.TABLE_NAME)
@NoArgsConstructor
public class Produto {

    public static final String TABLE_NAME = "Produto";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "fk_categoria_produto_id")
    private CategoriaProduto categoriaProduto;

    @ManyToOne
    @JoinColumn(name = "venda_id")
    private Vendas venda;

}
