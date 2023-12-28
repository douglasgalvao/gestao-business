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

import com.gestaobusiness.controleestoque.models.Cliente;
import com.gestaobusiness.controleestoque.services.ClienteService;

@Controller
@RequestMapping(value = "/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> getCategorias() {
        return ResponseEntity.ok().body(clienteService.obterClientes());
    };

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> obterCliente(@PathVariable Long id) {
        return ResponseEntity.ok().body(clienteService.obterCliente(id));
    };

    @PostMapping
    @ResponseBody
    public ResponseEntity<HttpStatus> salvarCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.status(201).body(clienteService.salvarCliente(cliente));
    };

}
