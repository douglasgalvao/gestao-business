package com.gestaobusiness.controleestoque.dtos;

import java.text.DecimalFormat;

import com.gestaobusiness.controleestoque.models.Categoria;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProdutoRequestDTO {

    private Long id;

    private String codBarras;

    private String nome;

    private Double preco;

    private Integer quantidade;

    private String img;

    private Double subtotal;

    private Categoria categoria_Produto;

    public double getSubTotal() {
        DecimalFormat df = new DecimalFormat("#0.00");
        String formattedTotal = df.format(subtotal);
        this.subtotal = Double.parseDouble(formattedTotal.replace(",", "."));
        return this.subtotal;
    }

}
