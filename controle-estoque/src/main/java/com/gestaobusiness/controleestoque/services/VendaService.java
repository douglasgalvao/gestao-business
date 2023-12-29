package com.gestaobusiness.controleestoque.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.gestaobusiness.controleestoque.dtos.AdicionarEstoqueProduto;
import com.gestaobusiness.controleestoque.dtos.ProdutoDTO;
import com.gestaobusiness.controleestoque.dtos.VendaDTO;
import com.gestaobusiness.controleestoque.dtos.VendaRequestDTO;
import com.gestaobusiness.controleestoque.mapper.ProdutoMapper;
import com.gestaobusiness.controleestoque.models.Cliente;
import com.gestaobusiness.controleestoque.models.ItemVenda;
import com.gestaobusiness.controleestoque.models.Produto;
import com.gestaobusiness.controleestoque.models.Venda;
import com.gestaobusiness.controleestoque.repository.ClienteRepository;
import com.gestaobusiness.controleestoque.repository.ItemVendaRepository;
import com.gestaobusiness.controleestoque.repository.VendaRepository;
import com.gestaobusiness.controleestoque.services.exceptions.EstoqueInsuficienteException;

import jakarta.transaction.Transactional;

@Service
public class VendaService {

    // @Autowired
    // ProdutoRepository produtoRepository;
    @Autowired
    VendaRepository vendaRepository;
    @Autowired
    ItemVendaRepository itemVendaRepository;
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ProdutoService produtoService;

    public List<Venda> obterVendas() {
        return vendaRepository.findAll();
        // return vendaRepository.findAll(Sort.by(Sort.Order.asc("id")));
    }

    public List<ItemVenda> obterVendas(Long idVenda) {
        return itemVendaRepository.findAllByVendaId(idVenda);
    }

    public HttpStatus salvarVenda(VendaRequestDTO venda) {
        Cliente cliente;
        if (venda.getCliente() != null) {
            cliente = clienteRepository.findById(venda.getCliente().getId()).get();
        } else {
            cliente = null;
        }

        venda.getProdutos().forEach(produtoVenda -> {
            Produto produto = produtoService.obterProdutoByCodBarras(produtoVenda.getCodBarras());
            if (produto == null) {
                throw new RuntimeException("Produto não encontrado");
            }
            if (produto.getQuantidadeEstoque() < produtoVenda.getQuantidade()) {
                throw new EstoqueInsuficienteException(produto.getNome(), produto.getQuantidadeEstoque(),
                        produtoVenda.getQuantidade());
            }
        });

        Venda vendaNova = new Venda();
        vendaNova.setCliente(cliente);
        vendaNova.setDataVenda(LocalDateTime.now());
        vendaNova.setMetodoPagamento(venda.getMetodoPagamento());
        vendaNova.setStatusVenda(venda.getStatusVenda());
        vendaNova.setTotalVenda(0.0);
        vendaNova.setProdutos(new ArrayList<>());
        final Venda vendaNovaFinal = vendaRepository.save(vendaNova);
        venda.getProdutos().forEach(produto -> {
            Produto produtoVenda = produtoService.obterProdutoByCodBarras(produto.getCodBarras());
            if (produtoVenda != null) {
                if (produto.getQuantidade() > 0) {
                    ItemVenda itemVenda = new ItemVenda();
                    itemVenda.setProduto(produtoVenda);
                    itemVenda.setQuantidade(produto.getQuantidade());
                    itemVenda.setSubtotal(produto.getQuantidade() * produtoVenda.getPreco());
                    itemVenda.setVenda(vendaNovaFinal);
                    itemVendaRepository.save(itemVenda);
                    produtoService.diminuirEstoque(
                            new AdicionarEstoqueProduto(produto.getCodBarras(), produto.getQuantidade()));
                    vendaNovaFinal.setTotalVenda(vendaNovaFinal.getTotalVenda() +
                            itemVenda.getSubTotal());
                    vendaNovaFinal.getProdutos().add(produtoVenda);
                    vendaNovaFinal.setProdutos(vendaNovaFinal.getProdutos());
                    vendaRepository.save(vendaNovaFinal);
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
            totalVenda += itemVenda.getSubTotal();
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
