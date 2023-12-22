package com.gestaobusiness.controleestoque.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = Cliente.TABLE_NAME)
@NoArgsConstructor
@Getter
@Setter
public class Cliente {

    public static final String TABLE_NAME = "Cliente";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "nome", nullable = false, length = 50)
    // @Size(min = 2, max = 50)
    private String nome;

    @Column(name = "numero", nullable = true, length = 50)
    // @Size(min = 2, max = 50)
    private String numero;

    @Column(name = "email", unique = true, nullable = true, length = 50)
    private String email;

}
