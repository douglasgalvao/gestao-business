package com.gestaobusiness.controleestoque.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.gestaobusiness.controleestoque.dtos.CodigoBarrasProdutoDTO;
import com.gestaobusiness.controleestoque.models.CodigoBarrasProduto;
import com.gestaobusiness.controleestoque.models.Estoque;
import com.gestaobusiness.controleestoque.models.Produto;
import com.gestaobusiness.controleestoque.repository.CodigoBarrasProdutoRepository;
import com.gestaobusiness.controleestoque.repository.EstoqueRepository;
import com.gestaobusiness.controleestoque.services.exceptions.EstoqueInsuficienteException;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CodigoBarrasProdutoRepository codigoBarrasProdutoRepository;

    public List<Estoque> obterEstoques() {
        return estoqueRepository.findAll();
    }

    public Estoque obterEstoqueByProdutoId(Long ProdutoId){
        return estoqueRepository.findByProdutoId(ProdutoId);
    }

    public Produto findProdutoByCodBarras(String codBarras) {
        return codigoBarrasProdutoRepository.findById(codBarras).get().getProduto();
    }

    public HttpStatus adicionarEstoqueAoProduto(CodigoBarrasProdutoDTO codBarrasProduto) {
        Estoque estoque = estoqueRepository.findByProdutoId(codBarrasProduto.getProdutoID());
        CodigoBarrasProduto codigoBarrasProduto = new CodigoBarrasProduto();
        codigoBarrasProduto.setEstoque(estoque);
        codigoBarrasProduto.setId_codBarras(codBarrasProduto.getCodBarras());
        codigoBarrasProduto.setProduto(produtoService.obterProduto(codBarrasProduto.getProdutoID()));
        codigoBarrasProdutoRepository.save(codigoBarrasProduto);
        estoque.setQuantidade(estoque.getQuantidade() + 1);
        estoqueRepository.save(estoque);
        return HttpStatus.ACCEPTED;
    }

    public HttpStatus diminuirEstoque(Long produtoID, Integer quantidade) {
        Estoque estoqueProduto = estoqueRepository.findById(produtoID).get();
        if (estoqueProduto != null && estoqueProduto.getQuantidade() >= quantidade) {
            estoqueProduto.setQuantidade(estoqueProduto.getQuantidade() - quantidade);
            estoqueRepository.save(estoqueProduto);
            return HttpStatus.ACCEPTED;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    public HttpStatus aumentarEstoque(Long produtoID, Integer quantidade) {
        Estoque estoqueProduto = estoqueRepository.findById(produtoID).get();
        if (estoqueProduto != null && quantidade > 0) {
            estoqueProduto.setQuantidade(estoqueProduto.getQuantidade() + quantidade);
            return HttpStatus.ACCEPTED;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }

}
