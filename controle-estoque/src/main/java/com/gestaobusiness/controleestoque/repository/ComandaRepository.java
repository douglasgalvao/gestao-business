package com.gestaobusiness.controleestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestaobusiness.controleestoque.models.Comanda;

public interface ComandaRepository extends JpaRepository<Comanda, Long> {
}
