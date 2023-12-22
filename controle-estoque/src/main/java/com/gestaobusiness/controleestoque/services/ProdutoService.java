package com.gestaobusiness.controleestoque.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.gestaobusiness.controleestoque.models.Categoria;
import com.gestaobusiness.controleestoque.models.Produto;
import com.gestaobusiness.controleestoque.repository.CategoriaRepository;
import com.gestaobusiness.controleestoque.repository.EstoqueRepository;
import com.gestaobusiness.controleestoque.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    CategoriaRepository categoriaRepository;

    public HttpStatus criarCategoria(Categoria categoria) {
        categoriaRepository.save(categoria);
        return HttpStatus.CREATED;
    }

    public List<Categoria> obterCategoria() {
        return categoriaRepository.findAll();
    }

    public List<Produto> obterProdutos() {
        return produtoRepository.findAll();
    }

    public Produto obterProduto(Long idProduto) {
        return produtoRepository.findById(idProduto)
                .orElseThrow(() -> new NoSuchElementException("Produto não encontrado com o ID: " + idProduto));
    }

    public HttpStatus salvarProduto(Produto categoria) {
        produtoRepository.save(categoria);
        return HttpStatus.CREATED;
    }

    public HttpStatus atualizarProduto(Produto categoria) {
        produtoRepository.save(categoria);
        return HttpStatus.OK;
    }

    public HttpStatus deletarProduto(Long idProduto) {
        produtoRepository.deleteById(idProduto);
        return HttpStatus.OK;
    }

}
