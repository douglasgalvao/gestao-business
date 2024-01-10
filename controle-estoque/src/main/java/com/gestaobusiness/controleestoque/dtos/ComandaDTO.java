package com.gestaobusiness.controleestoque.dtos;

import java.util.List;

import com.gestaobusiness.controleestoque.models.Cliente;
import com.gestaobusiness.controleestoque.models.ItemComanda;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComandaDTO {
    Cliente cliente;
    List<ItemComanda> itemsComanda;
    Double total;
}
