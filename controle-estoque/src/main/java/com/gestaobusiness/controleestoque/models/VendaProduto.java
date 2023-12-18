package com.gestaobusiness.controleestoque.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = VendaProduto.TABLE_NAME)
@Getter
@Setter
public class VendaProduto {

    public static final String TABLE_NAME = "Vendas_Produtos";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "venda_id", nullable = false)
    private Venda venda;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

}
