package com.gestaobusiness.controleestoque.dtos;

import java.util.List;

import com.gestaobusiness.controleestoque.models.Cliente;
import com.gestaobusiness.controleestoque.models.Produto;

import lombok.Getter;

@Getter
public class VendaDTO {

    private Long id;

    private Cliente cliente;

    private List<Produto> produtos;

}
