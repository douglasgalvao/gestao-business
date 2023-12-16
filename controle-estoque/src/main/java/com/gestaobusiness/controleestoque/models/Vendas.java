package com.gestaobusiness.controleestoque.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = Vendas.TABLE_NAME)
@NoArgsConstructor
public class Vendas {

    public static final String TABLE_NAME = "Vendas";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
    private List<Produto> produtos;
}
