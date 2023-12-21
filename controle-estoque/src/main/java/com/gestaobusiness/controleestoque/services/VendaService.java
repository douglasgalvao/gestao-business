package com.gestaobusiness.controleestoque.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.gestaobusiness.controleestoque.dtos.ProdutoDTO;
import com.gestaobusiness.controleestoque.dtos.VendaDTO;
import com.gestaobusiness.controleestoque.mapper.ProdutoMapper;
import com.gestaobusiness.controleestoque.models.Cliente;
import com.gestaobusiness.controleestoque.models.ItemVenda;
import com.gestaobusiness.controleestoque.models.Produto;
import com.gestaobusiness.controleestoque.models.Venda;
import com.gestaobusiness.controleestoque.repository.ClienteRepository;
import com.gestaobusiness.controleestoque.repository.ItemVendaRepository;
import com.gestaobusiness.controleestoque.repository.ProdutoRepository;
import com.gestaobusiness.controleestoque.repository.VendaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class VendaService {

    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    VendaRepository vendaRepository;
    @Autowired
    ItemVendaRepository itemVendaRepository;
    @Autowired
    ClienteRepository clienteRepository;

    public List<Venda> obterVendas() {
        return vendaRepository.findAll(Sort.by(Sort.Order.asc("id")));
    }

    public List<ItemVenda> obterVendas(Long idVenda) {
        return itemVendaRepository.findAllByVendaId(idVenda);
    }

    public Venda obterVenda(Long idVenda) {
        return vendaRepository.findById(idVenda)
                .orElseThrow(() -> new NoSuchElementException("Venda não encontrada com o ID: " + idVenda));
    }

    public HttpStatus salvarVenda(VendaDTO venda) {
        Cliente cliente;
        if (venda.getCliente() != null) {
            cliente = clienteRepository.findById(venda.getCliente().getId()).get();
        } else {
            cliente = null;
        }
        double totalVenda = 0.0;
        Venda newVenda = new Venda();
        List<ProdutoDTO> produtoDTOs = new ArrayList<>();
        List<Produto> produtos = new ArrayList<>();
        venda.getProdutos().forEach(produto -> {
            produtoDTOs.add(
                    ProdutoMapper.toDTO(produtoRepository.findById(produto.getId()).get(), produto.getQuantidade()));
            produtos.add(produtoRepository.findById(produto.getId()).get());
        });
        newVenda.setProdutos(produtos);
        vendaRepository.save(newVenda);
        newVenda.setCliente(cliente);
        newVenda.setDataVenda(LocalDateTime.now());
        newVenda.setStatusVenda(venda.getStatusVenda());
        newVenda.setMetodoPagamento(venda.getMetodoPagamento());
        newVenda = vendaRepository.save(newVenda);

        for (ProdutoDTO produtoDTO : venda.getProdutos()) {
            Produto produto = produtoRepository.findById(produtoDTO.getId()).get();
            totalVenda += produto.getPreco() * produtoDTO.getQuantidade();
        }
        newVenda.setTotalVenda(totalVenda);

        vendaRepository.save(newVenda);

        for (ProdutoDTO produtoDTO : produtoDTOs) {
            ItemVenda itemVenda = new ItemVenda();
            itemVenda.setVenda(newVenda);
            itemVenda.setProduto(produtoRepository.findById(produtoDTO.getId()).get());
            itemVenda.setQuantidade(produtoDTO.getQuantidade());
            itemVenda.setSubtotal(produtoDTO.getPreco() * produtoDTO.getQuantidade());
            itemVendaRepository.save(itemVenda);
        }

        return HttpStatus.CREATED;
    }

    public VendaDTO obterVendaByID(Long idVenda) {
        List<ItemVenda> itemVendas = itemVendaRepository.findAllByVendaId(idVenda);
        return consolidarItensNaVenda(itemVendas);
    }

    private VendaDTO consolidarItensNaVenda(List<ItemVenda> itemVendas) {
        VendaDTO venda = new VendaDTO();
        List<ProdutoDTO> produtos = new ArrayList<>();
        Double totalVenda = 0.0;
        for (ItemVenda itemVenda : itemVendas) {
            venda.setId(itemVenda.getVenda().getId());
            venda.setCliente(itemVenda.getVenda().getCliente());
            venda.setStatusVenda(itemVenda.getVenda().getStatusVenda());
            venda.setMetodoPagamento(itemVenda.getVenda().getMetodoPagamento());
            venda.setDataVenda(itemVenda.getVenda().getDataVenda());
            totalVenda += itemVenda.getSubtotal();
            ProdutoDTO produtoDTO = ProdutoMapper.toDTO(itemVenda.getProduto(), itemVenda.getQuantidade());
            produtos.add(produtoDTO);
        }
        venda.setTotalVenda(totalVenda);
        venda.setProdutos(produtos);
        return venda;
    }

    @Transactional
    public HttpStatus deletarVenda(Long idVenda) {
        itemVendaRepository.atualizarReferenciaProdutoByVenda(idVenda);
        vendaRepository.deleteById(idVenda);

        return HttpStatus.OK;
    }

}
