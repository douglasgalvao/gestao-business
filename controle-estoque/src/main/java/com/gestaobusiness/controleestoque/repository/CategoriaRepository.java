package com.gestaobusiness.controleestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestaobusiness.controleestoque.models.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    public Categoria findByNome(String nomeCategoria);
}
