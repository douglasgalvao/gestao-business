package com.gestaobusiness.controleestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestaobusiness.controleestoque.models.CodigoBarrasProduto;

public interface CodigoBarrasProdutoRepository extends JpaRepository<CodigoBarrasProduto, String> {

}
