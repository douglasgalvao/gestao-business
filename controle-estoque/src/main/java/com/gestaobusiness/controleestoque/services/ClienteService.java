package com.gestaobusiness.controleestoque.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.gestaobusiness.controleestoque.models.Cliente;
import com.gestaobusiness.controleestoque.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> obterClientes() {
        return clienteRepository.findAll();
    }

    public Cliente obterCliente(Long idCliente) {
        return clienteRepository.findById(idCliente)
                .orElseThrow(() -> new NoSuchElementException("Cliente não encontrada com o ID: " + idCliente));
    }

    public HttpStatus salvarCliente(Cliente cliente) {
        clienteRepository.save(cliente);
        return HttpStatus.CREATED;
    }

}
