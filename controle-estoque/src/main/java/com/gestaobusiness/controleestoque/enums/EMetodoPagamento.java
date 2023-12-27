package com.gestaobusiness.controleestoque.enums;

public enum EMetodoPagamento {
    DINHEIRO("dinheiro"),
    PIX("pix"),
    CARTAO_CREDITO("cartao_credito"),
    CARTAO_DEBITO("cartao_debito");

    private final String metodoPagamento;

    EMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public String getMetodoPagamento() {
        return this.metodoPagamento;
    }

}
