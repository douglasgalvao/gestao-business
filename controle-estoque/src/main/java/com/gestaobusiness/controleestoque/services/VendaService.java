package com.gestaobusiness.controleestoque.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.gestaobusiness.controleestoque.models.Venda;
import com.gestaobusiness.controleestoque.repository.VendaRepository;

@Service
public class VendaService {

    @Autowired
    VendaRepository vendaRepository;

    public List<Venda> getVendas() {
        return vendaRepository.findAll();
    }

    public Venda getVenda(Long idVenda) {
        return vendaRepository.findById(idVenda)
                .orElseThrow(() -> new NoSuchElementException("Venda não encontrada com o ID: " + idVenda));
    }

    public HttpStatus postVenda(Venda venda) {
        vendaRepository.save(venda);
        return HttpStatus.CREATED;
    }

}
