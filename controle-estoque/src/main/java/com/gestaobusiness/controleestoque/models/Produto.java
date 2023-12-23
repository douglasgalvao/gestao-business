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
    @Column(name = "produto_id", unique = true)
    private Long id;

    @Column(name = "produto_nome")
    private String nome;

    @Column(name = "produto_preco")
    private Double preco;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Categoria categoriaProduto;

}
