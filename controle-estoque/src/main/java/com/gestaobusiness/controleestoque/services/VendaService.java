package com.gestaobusiness.controleestoque.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.gestaobusiness.controleestoque.dtos.VendaDTO;
import com.gestaobusiness.controleestoque.models.Cliente;
import com.gestaobusiness.controleestoque.models.Produto;
import com.gestaobusiness.controleestoque.models.Venda;
import com.gestaobusiness.controleestoque.models.VendaProduto;
import com.gestaobusiness.controleestoque.repository.ClienteRepository;
import com.gestaobusiness.controleestoque.repository.VendaProdutoRepository;
import com.gestaobusiness.controleestoque.repository.VendaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class VendaService {

    @Autowired
    VendaRepository vendaRepository;
    @Autowired
    VendaProdutoRepository vendaProdutoRepository;
    @Autowired
    ClienteRepository clienteRepository;

    public List<Venda> obterVendas() {
        return vendaRepository.findAll();
    }

    public Venda obterVenda(Long idVenda) {
        return vendaRepository.findById(idVenda)
                .orElseThrow(() -> new NoSuchElementException("Venda não encontrada com o ID: " + idVenda));
    }

    public HttpStatus salvarVenda(VendaDTO venda) {

        Cliente cliente = clienteRepository.findById(venda.getCliente().getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Cliente não encontrado com o ID: " + venda.getCliente().getId()));

        Venda newVenda = new Venda();
        newVenda.setCliente(cliente);
        newVenda = vendaRepository.save(newVenda);
        List<Produto> produtos = venda.getProdutos();
        VendaProduto vendaProduto = new VendaProduto();

        for (Produto produto : produtos) {
            vendaProduto = new VendaProduto();
            vendaProduto.setProduto(produto);
            vendaProduto.setVenda(newVenda);
            System.out.println(vendaProduto.getProduto().getId() + " " +
                    vendaProduto.getVenda().getId());
            vendaProdutoRepository.save(vendaProduto);
        }

        return HttpStatus.CREATED;
    }

}
