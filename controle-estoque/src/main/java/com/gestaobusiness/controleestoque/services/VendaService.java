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
import com.gestaobusiness.controleestoque.repository.ClienteRepository;
import com.gestaobusiness.controleestoque.repository.VendaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class VendaService {

    @Autowired
    VendaRepository vendaRepository;
    // @Autowired
    // VendaProdutoRepository vendaProdutoRepository;
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
        newVenda.setProdutos(venda.getProdutos());
        newVenda.setStatusVenda(venda.getStatusVenda());
        newVenda.setMetodoPagamento(venda.getMetodoPagamento());
        newVenda = vendaRepository.save(newVenda);

        double totalVenda = 0.0;
        // Verificar se há produtos na lista e se nenhum deles é nulo antes de calcular
        // o total
        if (newVenda.getProdutos() != null) {
            for (Produto produto : newVenda.getProdutos()) {
                if (produto != null && produto.getPreco() != null) {
                    totalVenda += produto.getPreco();
                } else {

                    System.out.println("Produto nulo");
                }
            }
        } else {
            System.out.println("Não há produtos na lista");
        }

        newVenda.setTotalVenda(totalVenda);
        System.out.println(newVenda.getTotalVenda());
        vendaRepository.save(newVenda);

        return HttpStatus.CREATED;
    }

}
