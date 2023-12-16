package com.gestaobusiness.controleestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestaobusiness.controleestoque.models.Vendas;

public interface VendasRepository extends JpaRepository<Vendas, Long> {

}
