package com.gestaobusiness.controleestoque.dtos;

import com.gestaobusiness.controleestoque.models.Categoria;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProdutoDTO {

    private Long id;

    private String nome;

    private Double preco;

    private Integer quantidade;

    private Double subtotal;

    private Categoria categoriaProduto;

}
