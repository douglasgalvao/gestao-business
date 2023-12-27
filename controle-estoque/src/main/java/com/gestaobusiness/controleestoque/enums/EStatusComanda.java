package com.gestaobusiness.controleestoque.enums;

public enum EStatusComanda {
    PAGO("pago"),
    PENDENTE("pendente");

    private final String statuscomanda;

    EStatusComanda(String statuscomanda) {
        this.statuscomanda = statuscomanda;
    }

    public String getStatusComanda() {
        return this.statuscomanda;
    }

}
