package com.gestaobusiness.controleestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestaobusiness.controleestoque.models.VendaProduto;

public interface VendaProdutoRepository extends JpaRepository<VendaProduto, Long> {

}