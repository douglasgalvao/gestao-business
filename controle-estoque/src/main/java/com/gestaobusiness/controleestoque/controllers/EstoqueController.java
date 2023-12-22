package com.gestaobusiness.controleestoque.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gestaobusiness.controleestoque.dtos.EstoqueDTO;
import com.gestaobusiness.controleestoque.models.Produto;
import com.gestaobusiness.controleestoque.services.EstoqueService;

@Controller
@RequestMapping(value = "/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @PostMapping("/criar")
    public ResponseEntity<HttpStatus> criarEstoque(@RequestBody EstoqueDTO request) {
        Produto produto = request.getProduto();
        Integer quantidadeMinima = request.getQuantidadeMinima();
        Integer quantidadeDisponivel = request.getQuantidadeDisponivel();
        return new ResponseEntity<>(estoqueService.criarEstoque(produto, quantidadeMinima, quantidadeDisponivel));
    }
}
