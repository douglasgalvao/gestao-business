package com.gestaobusiness.controleestoque.services.exceptions;

public class EstoqueExistenteException extends Exception {

    private Long produtoId;

    public EstoqueExistenteException(Long produtoId) {
        super(String.format("Já existe um estoque cadastrado para o produto com ID %d.", produtoId));
        this.produtoId = produtoId;
    }

    public Long getProdutoId() {
        return produtoId;
    }
}
