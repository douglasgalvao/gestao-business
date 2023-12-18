// package com.gestaobusiness.controleestoque.repository;

// import java.util.List;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;

// import com.gestaobusiness.controleestoque.models.VendaProduto;

// import jakarta.persistence.Tuple;

// public interface VendaProdutoRepository extends JpaRepository<VendaProduto, Long> {
//     @Query(value = "select * from public.\"vendas_produtos_VIEW\"", nativeQuery = true)
//     List<Tuple> findAllVendaProdutosTuples();
// }