package com.gestaobusiness.controleestoque.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gestaobusiness.controleestoque.dtos.ProdutoDTO;
import com.gestaobusiness.controleestoque.dtos.VendaDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = ItemVenda.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
public class ItemVenda {

    public static final String TABLE_NAME = "ItemVenda";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "venda_id")
    private Venda venda;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Column(name = "quantidade")
    private int quantidade;

    @Column(name = "subtotal")
    private double subtotal;

}