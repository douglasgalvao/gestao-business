package com.gestaobusiness.controleestoque.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.gestaobusiness.controleestoque.dtos.AdicionarEstoqueProduto;
import com.gestaobusiness.controleestoque.models.Categoria;
import com.gestaobusiness.controleestoque.models.Produto;
import com.gestaobusiness.controleestoque.repository.CategoriaRepository;
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

    public Produto obterProdutoByNome(String nomeProduto) {
        return produtoRepository.findByNome(nomeProduto);
    }

    public Produto obterProdutoByCodBarras(String codBarras) {
        return produtoRepository.findByCodBarras(codBarras);
    }

    public HttpStatus salvarProduto(Produto produto) {
        produto.setQuantidadeEstoque(0);
        produtoRepository.save(produto);
        return HttpStatus.CREATED;
    }

    public HttpStatus adicionarEstoque(AdicionarEstoqueProduto item) {
        Produto produto = produtoRepository.findByCodBarras(item.getCodBarras());
        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + item.getQuantidade());
        produtoRepository.save(produto);
        return HttpStatus.OK;
    }

    public HttpStatus diminuirEstoque(AdicionarEstoqueProduto item) {
        if (item.getQuantidade() <= 0) {
            throw new RuntimeException("Quantidade não pode ser adicionada");
        }
        Produto produto = produtoRepository.findByCodBarras(item.getCodBarras());
        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - item.getQuantidade());
        produtoRepository.save(produto);
        return HttpStatus.OK;
    }

    public HttpStatus deletarProduto(Long idProduto) {
        produtoRepository.deleteById(idProduto);
        return HttpStatus.OK;
    }

}
