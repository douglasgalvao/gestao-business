package com.gestaobusiness.controleestoque.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gestaobusiness.controleestoque.enums.EStatusComanda;

@NoArgsConstructor
@Entity
@Table(name = "Comandas")
@Getter
@Setter
public class Comanda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @OneToMany(mappedBy = "comanda", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ItemComanda> itemsComanda = new ArrayList<>();

    @Column(name = "data")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime data;

    @Column(name = "total")
    private Double total;

    private EStatusComanda statusComanda;

    public Comanda(Cliente cliente, LocalDateTime data, Double total) {
        this.cliente = cliente;
        this.data = data;
        this.total = total;
    }
}
