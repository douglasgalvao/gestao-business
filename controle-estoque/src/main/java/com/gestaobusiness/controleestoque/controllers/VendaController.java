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

import com.gestaobusiness.controleestoque.dtos.VendaRequestDTO;
import com.gestaobusiness.controleestoque.models.Venda;
import com.gestaobusiness.controleestoque.services.VendaService;
import com.gestaobusiness.controleestoque.services.exceptions.EstoqueInsuficienteException;

@Controller
@RequestMapping(value = "/venda")
public class VendaController {

    @Autowired
    VendaService vendaService;

    @GetMapping
    public ResponseEntity<List<Venda>> obterVendas() {
        return ResponseEntity.ok().body(vendaService.obterVendas());
    };

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> obterVenda(@PathVariable Long id) {
        return ResponseEntity.ok().body(vendaService.obterVendaByID(id));
    };

    @PostMapping
    public ResponseEntity<?> salvarVenda(@RequestBody VendaRequestDTO venda) {
        try {
            return ResponseEntity.ok().body(vendaService.salvarVenda(venda));
        } catch (EstoqueInsuficienteException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deletarVenda(@PathVariable Long id) {
        return ResponseEntity.ok().body(vendaService.deletarVenda(id));
    };

}
