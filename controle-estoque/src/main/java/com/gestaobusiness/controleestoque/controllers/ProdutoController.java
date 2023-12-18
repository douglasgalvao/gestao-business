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
import org.springframework.web.bind.annotation.ResponseBody;

import com.gestaobusiness.controleestoque.models.Produto;
import com.gestaobusiness.controleestoque.services.ProdutoService;

@Controller
@RequestMapping(value = "/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> getProdutos() {
        return ResponseEntity.ok().body(produtoService.obterProdutos());
    };

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> obterProduto(@PathVariable Long id) {
        return ResponseEntity.ok().body(produtoService.obterProduto(id));
    };

    @PostMapping
    @ResponseBody
    public ResponseEntity<HttpStatus> salvarProduto(@RequestBody Produto produto) {
        return ResponseEntity.status(201).body(produtoService.salvarProduto(produto));
    };
}
