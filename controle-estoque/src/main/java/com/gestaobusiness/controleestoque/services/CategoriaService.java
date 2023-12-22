package com.gestaobusiness.controleestoque.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.gestaobusiness.controleestoque.models.Categoria;
import com.gestaobusiness.controleestoque.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    public List<Categoria> obterCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria obterCategoria(Long idCategoria) {
        return categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new NoSuchElementException("Categoria não encontrada com o ID: " + idCategoria));
    }

    public Categoria obterCategoria(String nomeCategoria) {
        return categoriaRepository.findByNome(nomeCategoria);
    }

    public HttpStatus salvarCategoria(Categoria categoria) {
        categoriaRepository.save(categoria);
        return HttpStatus.CREATED;
    }

    public HttpStatus atualizarCategoria(Categoria categoria) {
        categoriaRepository.save(categoria);
        return HttpStatus.OK;
    }

    public HttpStatus deletarCategoria(Long idCategoria) {
        categoriaRepository.deleteById(idCategoria);
        return HttpStatus.OK;
    }

}
