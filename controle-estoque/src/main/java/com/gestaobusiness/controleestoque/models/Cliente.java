package com.gestaobusiness.controleestoque.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Cliente {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "nome", nullable = false, length = 50)
    // @Size(min = 2, max = 50)
    private String nome;

    @Column(name = "senha", nullable = false, length = 45)
    // @Size(min = 8, max = 100)
    private String senha;

    @Column(name = "email", unique = true, nullable = false, length = 50)
    private String email;
}
