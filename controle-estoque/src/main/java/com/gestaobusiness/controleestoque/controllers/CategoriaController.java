package com.gestaobusiness.controleestoque.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gestaobusiness.controleestoque.models.Categoria;
import com.gestaobusiness.controleestoque.services.CategoriaService;

@Controller
@RequestMapping(value = "/categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<Categoria>> getCategorias() {
        return ResponseEntity.ok().body(categoriaService.obterCategorias());
    };

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> obterCategoria(@PathVariable Long id) {
        return ResponseEntity.ok().body(categoriaService.obterCategoria(id));
    };

    @GetMapping(value = "/nome/{nome}")
    public ResponseEntity<?> obterCategoria(@PathVariable String nome) {
        return ResponseEntity.ok().body(categoriaService.obterCategoria(nome));
    };

    @PostMapping
    @ResponseBody
    public ResponseEntity<HttpStatus> salvarCategoria(@RequestBody Categoria categoria) {
        return ResponseEntity.status(201).body(categoriaService.salvarCategoria(categoria));
    };

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deletarCategoria(@PathVariable Long id) {
        categoriaService.deletarCategoria(id);
        return ResponseEntity.ok().build();
    };

}
