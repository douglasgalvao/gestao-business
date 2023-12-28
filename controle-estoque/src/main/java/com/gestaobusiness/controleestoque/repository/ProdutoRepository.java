package com.gestaobusiness.controleestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestaobusiness.controleestoque.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    public Produto findByNome(String nomeProduto);
    public Produto findByCodBarras(String codBarras);
}
