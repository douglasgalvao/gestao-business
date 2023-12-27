package com.gestaobusiness.controleestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestaobusiness.controleestoque.models.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
    public Estoque findByProdutoId(Long id);
}
