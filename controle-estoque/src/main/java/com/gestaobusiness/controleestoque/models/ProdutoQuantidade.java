package com.gestaobusiness.controleestoque.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoQuantidade {
    private Produto produto;
    private int quantidade;
    private double subtotal;
}
