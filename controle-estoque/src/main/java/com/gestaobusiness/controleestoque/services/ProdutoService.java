package com.gestaobusiness.controleestoque.services;

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
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.gestaobusiness.controleestoque.dtos.AdicionarEstoqueProduto;
import com.gestaobusiness.controleestoque.dtos.ProdutoFileResponseDTO;
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

    public ProdutoFileResponseDTO uploadImagem(MultipartFile file) {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        HttpHeaders headers = new HttpHeaders();

        // ContentType
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultipartBodyBuilder multipartBodyBuilder = new MultipartBodyBuilder();
        multipartBodyBuilder.part("file", file.getResource());
        multipartBodyBuilder.part("UPLOADCARE_PUB_KEY", "fb4b6a885814a953c3d7");
        multipartBodyBuilder.part("UPLOADCARE_STORE", "auto");

        MultiValueMap<String, HttpEntity<?>> multipartBody = multipartBodyBuilder.build();

        // The complete http request body.
        HttpEntity<MultiValueMap<String, HttpEntity<?>>> httpEntity = new HttpEntity<>(multipartBody, headers);

        ResponseEntity<ProdutoFileResponseDTO> response = restTemplate.postForEntity(
                "https://upload.uploadcare.com/base/?jsonerrors=1", httpEntity,
                ProdutoFileResponseDTO.class);

        if (response.getBody().getFile() == null) {
            ProdutoFileResponseDTO p = new ProdutoFileResponseDTO();
            p.setFile("null");
            return p;
        }

        return response.getBody();

    }

}