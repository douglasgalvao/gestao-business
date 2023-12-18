package com.gestaobusiness.controleestoque.dtos;

import java.util.List;

import com.gestaobusiness.controleestoque.models.Cliente;
import com.gestaobusiness.controleestoque.models.Produto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VendaProdutoInfoDTO {

    private Long id;

    private Cliente cliente;

    private List<Produto> produtos;

}




