package com.gestaobusiness.controleestoque.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ItemComanda")
@Getter
@Setter
@NoArgsConstructor
public class ItemComanda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "produto_id") 
    private Produto produto;

    @Column(name = "quantidade")
    private int quantidade;

    @Column(name = "subtotal")
    private double subtotal;

    @ManyToOne
    @JoinColumn(name = "comanda_id")
    @JsonBackReference
    private Comanda comanda;

    public ItemComanda(Produto produto, int quantidade, double subtotal, Comanda comanda) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.subtotal = subtotal;
        this.comanda = comanda;
    }
}
