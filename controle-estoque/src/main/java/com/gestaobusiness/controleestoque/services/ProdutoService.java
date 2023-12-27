package com.gestaobusiness.controleestoque.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.gestaobusiness.controleestoque.models.Categoria;
import com.gestaobusiness.controleestoque.models.Estoque;
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
    @Autowired
    EstoqueRepository estoqueRepository;

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

    public Produto obterProdutoByNome(String nomeProduto) {
        return produtoRepository.findByNome(nomeProduto);
    }

    public HttpStatus salvarProduto(Produto produto) {
        Produto produtoSaved = produtoRepository.save(produto);
        Estoque estoque = new Estoque();
        estoque.setId(produtoSaved.getId());
        estoque.setProduto(produtoSaved);
        estoque.setQuantidade(0);
        estoqueRepository.save(estoque);
        return HttpStatus.CREATED;
    }

    public HttpStatus atualizarProduto(Produto produto) {
        produtoRepository.save(produto);
        return HttpStatus.OK;
    }

    public HttpStatus deletarProduto(Long idProduto) {
        produtoRepository.deleteById(idProduto);
        return HttpStatus.OK;
    }

}
