package com.gestaobusiness.controleestoque.enums;

public enum EStatusVenda {
    PENDENTE("pendente"),
    PAGO("pago");

    private final String statusVenda;

    EStatusVenda(String statusVenda) {
        this.statusVenda = statusVenda;
    }

    public String getStatusVenda() {
        return this.statusVenda;
    }

}
