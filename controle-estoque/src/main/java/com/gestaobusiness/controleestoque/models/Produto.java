package com.gestaobusiness.controleestoque.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = Produto.TABLE_NAME)
@NoArgsConstructor
@Getter
@Setter
public class Produto {

    public static final String TABLE_NAME = "Produto";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(unique = true, name = "cod_barras")
    private String codBarras;

    @Column(name = "nome")
    private String nome;

    @Column(name = "preco")
    private Double preco;

    @Column(name = "img")
    private String img;

    @Column(name = "estoque", columnDefinition = "integer default 0")
    Integer quantidadeEstoque;

    @Column(name = "estoque_minimo", columnDefinition = "integer default 10")
    Integer quantidadeMinimaEstoque;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Categoria categoriaProduto;

}
