package com.gestaobusiness.controleestoque.services;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.gestaobusiness.controleestoque.dtos.AdicionarEstoqueProduto;
import com.gestaobusiness.controleestoque.dtos.ProdutoFileResponseDTO;
import com.gestaobusiness.controleestoque.dtos.ProdutoRequestDTO;
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
        if (produto.getQuantidadeEstoque() == null) {
            produto.setQuantidadeEstoque(0);
        }
        System.out.println(produto.getImg());
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

// public HttpStatus uploadImagem(MultipartFile file) {
// RestTemplate restTemplate = new RestTemplate();

// System.out.println(file);
// // Criar headers
// HttpHeaders headers = new HttpHeaders();
// headers.setContentType(MediaType.MULTIPART_FORM_DATA);

// // Criar o corpo da solicitação
// MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
// body.add("file", file);
// body.add("UPLOADCARE_PUB_KEY", "fb4b6a885814a953c3d7");
// body.add("UPLOADCARE_STORE", "auto");
// body.add("source", "local");
// String urlEndpoint = "https://upload.uploadcare.com/base/?jsonerrors=1";

// // Criar a solicitação
// HttpEntity<MultiValueMap<String, Object>> requestEntity = new
// HttpEntity<>(body, headers);

// // Enviar a solicitação
// ResponseEntity<ProdutoFileResponseDTO> response =
// restTemplate.postForEntity(urlEndpoint, requestEntity,
// ProdutoFileResponseDTO.class);
// // System.out.println(response.getBody());
// return HttpStatus.OK;

// }
