package com.gestaobusiness.controleestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestaobusiness.controleestoque.models.Fornecedor;

public interface FornecedoresRepository extends JpaRepository<Fornecedor, Long> {

}
