package com.gestaobusiness.controleestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestaobusiness.controleestoque.models.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {

}