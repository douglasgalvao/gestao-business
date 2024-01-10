package com.gestaobusiness.controleestoque.enums;

public enum EStatusComanda {
    PAGO("PAGO"),
    PARCIALMENTE_PAGO("PARCIALMENTE PAGO"),
    PENDENTE("PENDENTE");

    private final String statuscomanda;

    EStatusComanda(String statuscomanda) {
        this.statuscomanda = statuscomanda;
    }

    public String getStatusComanda() {
        return this.statuscomanda;
    }

}
