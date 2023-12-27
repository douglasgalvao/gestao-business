package com.gestaobusiness.controleestoque.services.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EstoqueInsuficienteException extends Exception {

    private String produto;
    private int quantidadeEmEstoque;
    private int quantidadeDesejada;

    public EstoqueInsuficienteException(String produto, int quantidadeEmEstoque, int quantidadeDesejada) {
        super(String.format("Estoque insuficiente para vender %d unidades do produto %s. Estoque disponível: %d",
                quantidadeDesejada, produto, quantidadeEmEstoque));

        this.produto = produto;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        this.quantidadeDesejada = quantidadeDesejada;
    }
}
