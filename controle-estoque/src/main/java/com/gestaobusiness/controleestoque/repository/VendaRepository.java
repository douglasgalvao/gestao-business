package com.gestaobusiness.controleestoque.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gestaobusiness.controleestoque.models.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {
    List<Venda> findAll(Sort sort);

    @Query(value = "SELECT * FROM venda WHERE data_venda BETWEEN cast(:startDate as timestamp) AND cast(:endDate as timestamp)", nativeQuery = true)
    List<Venda> findAllByDataVendaBetween(LocalDateTime startDate, LocalDateTime endDate);
}