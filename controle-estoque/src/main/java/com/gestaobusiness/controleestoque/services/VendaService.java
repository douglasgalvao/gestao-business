package com.gestaobusiness.controleestoque.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.gestaobusiness.controleestoque.dtos.ProdutoDTO;
import com.gestaobusiness.controleestoque.dtos.VendaDTO;
import com.gestaobusiness.controleestoque.dtos.VendaRequestDTO;
import com.gestaobusiness.controleestoque.mapper.ProdutoMapper;
import com.gestaobusiness.controleestoque.models.Cliente;
import com.gestaobusiness.controleestoque.models.Estoque;
import com.gestaobusiness.controleestoque.models.ItemVenda;
import com.gestaobusiness.controleestoque.models.Produto;
import com.gestaobusiness.controleestoque.models.Venda;
import com.gestaobusiness.controleestoque.repository.ClienteRepository;
import com.gestaobusiness.controleestoque.repository.ItemVendaRepository;
import com.gestaobusiness.controleestoque.repository.ProdutoRepository;
import com.gestaobusiness.controleestoque.repository.VendaRepository;
import com.gestaobusiness.controleestoque.services.exceptions.EstoqueInsuficienteException;

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

    @Autowired
    EstoqueService estoqueService;

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

    public HttpStatus salvarVenda(VendaRequestDTO venda) {
        Cliente cliente;
        final boolean validation = true;
        if (venda.getCliente() != null) {
            cliente = clienteRepository.findById(venda.getCliente().getId()).get();
            System.out.println(cliente.getNome());
        } else {
            cliente = null;
        }

        venda.getProdutos().forEach(produto -> {
            Produto p = estoqueService.findProdutoByCodBarras(produto.getCodbarras());
            Estoque estoqueProduto = estoqueService.obterEstoqueByProdutoId(p.getId());
            if (estoqueProduto.getQuantidade() < produto.getQuantidade()) {
                validation = false;
            }
        });

        venda.getProdutos().forEach(produto -> {
            Produto produtoVenda = estoqueService.findProdutoByCodBarras(produto.getCodbarras());
            HttpStatus statusTransacao;
            if (produtoVenda != null) {
                if (produto.getQuantidade() > 0) {
                    Venda vendaNova = new Venda();
                    vendaNova.setCliente(cliente);
                    vendaNova.setDataVenda(LocalDateTime.now());
                    vendaNova.setMetodoPagamento(venda.getMetodoPagamento());
                    vendaNova.setStatusVenda(venda.getStatusVenda());
                    vendaNova.setTotalVenda(venda.getTotalVenda());
                    vendaNova = vendaRepository.save(vendaNova);
                    ItemVenda itemVenda = new ItemVenda();
                    itemVenda.setProduto(produtoVenda);
                    itemVenda.setQuantidade(produto.getQuantidade());
                    itemVenda.setSubtotal(produto.getQuantidade() * produtoVenda.getPreco());
                    itemVenda.setVenda(vendaNova);
                    itemVendaRepository.save(itemVenda);
                    estoqueService.diminuirEstoque(produtoVenda.getId(), produto.getQuantidade());
                }
            }
        });
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
