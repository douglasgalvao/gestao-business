package com.gestaobusiness.controleestoque.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gestaobusiness.controleestoque.dtos.CodigoBarrasProdutoDTO;
import com.gestaobusiness.controleestoque.models.Estoque;
import com.gestaobusiness.controleestoque.models.Produto;
import com.gestaobusiness.controleestoque.services.EstoqueService;

@Controller
@RequestMapping(value = "/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @GetMapping
    public ResponseEntity<List<Estoque>> obterEstoques() {
        return ResponseEntity.status(HttpStatus.OK).body(estoqueService.obterEstoques());
    }

    @GetMapping(value = "/produto/{codBarras}")
    public ResponseEntity<Produto> obterEstoque(@PathVariable String codBarras) {
        return ResponseEntity.status(HttpStatus.OK).body(estoqueService.findProdutoByCodBarras(codBarras));
    }

    @PostMapping(value = "/produto")
    public ResponseEntity<HttpStatus> adicionarEstoqueAoProduto(@RequestBody CodigoBarrasProdutoDTO codBarras) {
        return ResponseEntity.status(HttpStatus.OK).body(estoqueService.adicionarEstoqueAoProduto(codBarras));
    }

}
