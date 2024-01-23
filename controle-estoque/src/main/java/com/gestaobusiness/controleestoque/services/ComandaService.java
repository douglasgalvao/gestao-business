package com.gestaobusiness.controleestoque.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestaobusiness.controleestoque.dtos.ComandaDTO;
import com.gestaobusiness.controleestoque.enums.EStatusComanda;
import com.gestaobusiness.controleestoque.models.Comanda;
import com.gestaobusiness.controleestoque.models.ItemComanda;
import com.gestaobusiness.controleestoque.repository.ClienteRepository;
import com.gestaobusiness.controleestoque.repository.ComandaRepository;
import com.gestaobusiness.controleestoque.repository.VendaRepository;

@Service
public class ComandaService {

    @Autowired
    VendaRepository vendaRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ComandaRepository comandaRepository;

    public void registrarComanda(ComandaDTO comandaDTO) {
        Comanda comanda = new Comanda();
        comanda.setCliente(clienteRepository.findById(comandaDTO.getCliente().getId()).get());
        comanda.setData(LocalDateTime.now());
        comanda.setTotal(comandaDTO.getTotal());
        comanda.setStatusComanda(EStatusComanda.PENDENTE);
        List<ItemComanda> itemsComanda = new ArrayList<>();
        for (ItemComanda itemComanda : comandaDTO.getItemsComanda()) {
            itemComanda.setComanda(comanda);
            itemsComanda.add(itemComanda);
        }
        comanda.setItemsComanda(itemsComanda);
        comandaRepository.save(comanda);

    }

    public void adicionarItemComanda(Long idComanda, List<ItemComanda> itemComandas) {
        Comanda comanda = comandaRepository.findById(idComanda).get();
        for (ItemComanda itemComanda : itemComandas) {
            itemComanda.setComanda(comanda);
            comanda.getItemsComanda().add(itemComanda);
        }
        comandaRepository.save(comanda);
    }

    public void atualizarComanda(Comanda comandacliente) {
        comandaRepository.save(comandacliente);
    }

    public void deletarComanda(Comanda comandacliente) {
        comandaRepository.delete(comandacliente);
    }

    public Comanda buscarComanda(Long id) {
        return comandaRepository.findById(id).get();
    }

    public List<Comanda> buscarTodasComandas() {
        return comandaRepository.findAll();
    }

}
