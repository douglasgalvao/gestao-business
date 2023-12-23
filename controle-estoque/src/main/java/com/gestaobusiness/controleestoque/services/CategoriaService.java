package com.gestaobusiness.controleestoque.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.gestaobusiness.controleestoque.models.Categoria;
import com.gestaobusiness.controleestoque.repository.CategoriaRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    private EntityManager entityManager;

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

    @Transactional
    public HttpStatus salvarCategoria(Categoria categoria) {
        categoria = entityManager.merge(categoria);
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
