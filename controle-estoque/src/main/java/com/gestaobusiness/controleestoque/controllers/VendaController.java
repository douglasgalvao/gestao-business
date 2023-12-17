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

import com.gestaobusiness.controleestoque.models.Venda;
import com.gestaobusiness.controleestoque.services.VendaService;

@Controller
@RequestMapping(value = "/venda")
public class VendaController {

    @Autowired
    VendaService vendaService;

    @GetMapping
    public ResponseEntity<List<Venda>> getVendas() {
        return ResponseEntity.ok().body(vendaService.getVendas());
    };

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getVenda(@PathVariable Long id) {
        return ResponseEntity.ok().body(vendaService.getVenda(id));
    };

    @PostMapping
    public ResponseEntity<HttpStatus> postVenda(@RequestBody Venda venda) {
        return ResponseEntity.status(201).body(vendaService.postVenda(venda));
    };

}
