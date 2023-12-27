package com.gestaobusiness.controleestoque.services.exceptions;

public class ProdutoNaoEncontradoException extends Exception {

    private Long produtoId;

    public ProdutoNaoEncontradoException(Long produtoId) {
        super(String.format("Produto com ID %d não encontrado.", produtoId));
        this.produtoId = produtoId;
    }

    public Long getProdutoId() {
        return produtoId;
    }
}
