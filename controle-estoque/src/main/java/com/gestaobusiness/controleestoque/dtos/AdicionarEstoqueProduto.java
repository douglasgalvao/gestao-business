package com.gestaobusiness.controleestoque.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdicionarEstoqueProduto {
    private String codBarras;
    private Integer quantidade;
}
