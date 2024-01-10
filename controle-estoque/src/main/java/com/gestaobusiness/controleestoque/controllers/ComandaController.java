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
import com.gestaobusiness.controleestoque.dtos.ComandaDTO;
import com.gestaobusiness.controleestoque.models.Comanda;
import com.gestaobusiness.controleestoque.models.ItemComanda;
import com.gestaobusiness.controleestoque.services.ClienteService;
import com.gestaobusiness.controleestoque.services.ComandaService;
import com.gestaobusiness.controleestoque.services.ProdutoService;

@Controller
@RequestMapping(value = "/comanda")
public class ComandaController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    ProdutoService produtoService;

    @Autowired
    ComandaService comandaService;

    @PostMapping(value = "/criar")
    public ResponseEntity<HttpStatus> criarComanda(@RequestBody ComandaDTO comanda) {
        comandaService.registrarComanda(comanda);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{comandaId}")
    public ResponseEntity<Comanda> buscarComanda(@PathVariable Long comandaId) {
        Comanda comanda = comandaService.buscarComanda(comandaId);
        return new ResponseEntity<>(comanda, HttpStatus.OK);
    }

    @PostMapping(value = "/{comandaId}")
    public ResponseEntity<HttpStatus> adicionarItemComanda(@PathVariable Long comandaId,
            @RequestBody List<ItemComanda> itemComandas) {
        comandaService.adicionarItemComanda(comandaId, itemComandas);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Comanda>> buscarTodasComandas() {
        return ResponseEntity.status(HttpStatus.OK).body(comandaService.buscarTodasComandas());
    }

}
