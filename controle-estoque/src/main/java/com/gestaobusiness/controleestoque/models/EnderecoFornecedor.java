package com.gestaobusiness.controleestoque.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class EnderecoFornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "rua", nullable = false)
    private String rua;

    @Column(name = "cidade", nullable = false)
    private String cidade;

    @Column(name = "numero", nullable = false)
    private String numero;

}