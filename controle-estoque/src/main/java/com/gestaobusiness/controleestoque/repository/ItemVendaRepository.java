package com.gestaobusiness.controleestoque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestaobusiness.controleestoque.models.ItemVenda;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {
    List<ItemVenda> findAllByVendaId(Long idVenda);
}