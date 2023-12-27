package com.gestaobusiness.controleestoque.dtos;

import com.gestaobusiness.controleestoque.models.Produto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstoqueDTO {
    private Produto produto;
    private Integer quantidade;
}
