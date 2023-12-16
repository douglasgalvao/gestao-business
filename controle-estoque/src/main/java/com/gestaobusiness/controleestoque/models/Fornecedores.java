package com.gestaobusiness.controleestoque.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public class Fornecedores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "nome", nullable = false, length = 50)
    // @Size(min = 2, max = 50)
    private String nome;

    @Column(name = "img-url", nullable = false)
    private String imgUrl;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_endereco_id")
    private EnderecoFornecedor enderecoFornecedor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_contato_id")
    private ContatoFornecedor contatoFornecedor;



}
