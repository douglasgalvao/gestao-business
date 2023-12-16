package com.gestaobusiness.controleestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestaobusiness.controleestoque.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
