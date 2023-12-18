package com.gestaobusiness.controleestoque.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Collectors;

import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.gestaobusiness.controleestoque.dtos.VendaDTO;
import com.gestaobusiness.controleestoque.dtos.VendaProdutoInfoDTO;
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
        // List<Tuple> tuples = vendaProdutoRepository.findAllVendaProdutosTuples();
        // List<VendaProdutoInfo> vendasProdutoInfo = tuples.stream()
        // .map(VendaProdutoInfo::new)
        // .collect(Collectors.toList());

        // Map<Long, List<VendaProdutoInfo>> produtosPorVenda =
        // vendasProdutoInfo.stream()
        // .collect(Collectors.groupingBy(VendaProdutoInfo::getVendaId));

        // // Criar os objetos VendaProdutoInfoDTO
        // List<VendaProdutoInfoDTO> vendaProdutoInfoDTOs =
        // produtosPorVenda.entrySet().stream()
        // .map(entry -> {
        // VendaProdutoInfoDTO dto = new VendaProdutoInfoDTO();
        // dto.setId(entry.getKey());
        // // Aqui você precisará ajustar conforme sua lógica para obter Cliente e
        // Produtos
        // dto.setCliente(entry.getValue().get(0).getCliente());
        // dto.setProdutos(
        // entry.getValue().stream().map(VendaProdutoInfo::g).collect(Collectors.toList()));
        // return dto;
        // })
        // .collect(Collectors.toList());

        // return vendaProdutoInfoDTOs;
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
        newVenda.setProdutos(venda.getProdutos());
        vendaRepository.save(newVenda);

        return HttpStatus.CREATED;
    }

}
