package com.gestaobusiness.controleestoque.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class CategoriaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "nome", nullable = false, length = 50)
    // @Size(min = 2, max = 50)
    private String nome;

}
