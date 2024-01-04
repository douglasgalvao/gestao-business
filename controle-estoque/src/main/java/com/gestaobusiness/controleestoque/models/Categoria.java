package com.gestaobusiness.controleestoque.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = Categoria.TABLE_NAME)
@NoArgsConstructor
@Getter
@Setter
public class Categoria {

    public static final String TABLE_NAME = "Categoria";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoria_id", unique = true)
    private Long id;

    @Column(name = "categoria_nome", length = 50)
    private String nome;

}
