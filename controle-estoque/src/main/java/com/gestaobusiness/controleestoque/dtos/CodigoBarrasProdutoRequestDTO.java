package com.gestaobusiness.controleestoque.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CodigoBarrasProdutoRequestDTO {
    private String codbarras;
    private Integer quantidade;
}
