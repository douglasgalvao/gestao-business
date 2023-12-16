package com.gestaobusiness.controleestoque.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = ContatoFornecedor.TABLE_NAME)
@NoArgsConstructor
public class ContatoFornecedor {

    public static final String TABLE_NAME = "ContatoFornecedor";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "telefone", nullable = false, length = 50)
    // @Size(min = 2, max = 50)
    private String telefone;

    @Column(name = "email", unique = true, nullable = false, length = 50)
    private String email;

}
