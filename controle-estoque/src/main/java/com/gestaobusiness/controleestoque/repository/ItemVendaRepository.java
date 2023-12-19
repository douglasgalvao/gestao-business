package com.gestaobusiness.controleestoque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.gestaobusiness.controleestoque.models.ItemVenda;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {
    List<ItemVenda> findAllByVendaId(Long idVenda);

    List<ItemVenda> deleteAllByVendaId(Long idVenda);

    @Modifying
    @Query(value = "Delete from item_venda WHERE venda_id = :vendaId", nativeQuery = true)
    void atualizarReferenciaProdutoByVenda(Long vendaId);

}