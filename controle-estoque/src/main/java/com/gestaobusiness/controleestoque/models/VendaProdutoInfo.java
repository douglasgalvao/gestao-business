package com.gestaobusiness.controleestoque.models;

import jakarta.persistence.Tuple;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VendaProdutoInfo {
    private Long vendaId;
    private Long idCliente;
    private String nomeCliente;
    private String numeroCliente;
    private String emailCliente;
    private Long produtoId;
    private String produtoNome;

    public VendaProdutoInfo(Tuple tuple) {
        this.vendaId = tuple.get(0, Long.class);
        this.idCliente = tuple.get(1, Long.class);
        this.nomeCliente = tuple.get(2, String.class);
        this.numeroCliente = tuple.get(3, String.class);
        this.emailCliente = tuple.get(4, String.class);
        this.produtoId = tuple.get(5, Long.class);
        this.produtoNome = tuple.get(6, String.class);
    }
}
