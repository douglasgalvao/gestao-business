package com.gestaobusiness.controleestoque.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gestaobusiness.controleestoque.models.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {
    List<Venda> findAll(Sort sort);
}