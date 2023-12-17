package com.gestaobusiness.controleestoque.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = EnderecoFornecedor.TABLE_NAME)
@NoArgsConstructor
@Getter
public class EnderecoFornecedor {
    public static final String TABLE_NAME = "EnderecoFornecedor";
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