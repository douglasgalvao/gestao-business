package com.gestaobusiness.controleestoque.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.gestaobusiness.controleestoque.models.Estoque;
import com.gestaobusiness.controleestoque.models.Produto;
import com.gestaobusiness.controleestoque.repository.EstoqueRepository;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    public HttpStatus criarEstoque(Produto produto, Integer quantidadeMinima, Integer quantidadeDisponivel) {
        Estoque estoque = new Estoque();
        estoque.setProduto(produto);
        estoque.setQuantidadeMinima(quantidadeMinima);
        estoque.setQuantidadeDisponivel(quantidadeDisponivel);
        estoqueRepository.save(estoque);
        return HttpStatus.CREATED;
    }

}
