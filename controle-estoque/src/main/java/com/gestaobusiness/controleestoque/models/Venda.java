package com.gestaobusiness.controleestoque.models;

import java.time.LocalDateTime;
import java.util.List;

import com.gestaobusiness.controleestoque.enums.EMetodoPagamento;
import com.gestaobusiness.controleestoque.enums.EStatusVenda;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = Venda.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
public class Venda {

    public static final String TABLE_NAME = "Vendas";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    private Double totalVenda;

    private LocalDateTime dataVenda;

    private EStatusVenda statusVenda;

    private EMetodoPagamento metodoPagamento;

    @ManyToOne
    private Cliente cliente;

    @ManyToMany
    private List<Produto> produtos;

}
