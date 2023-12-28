package com.gestaobusiness.controleestoque.models;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.context.annotation.DependsOn;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gestaobusiness.controleestoque.enums.EMetodoPagamento;
import com.gestaobusiness.controleestoque.enums.EStatusVenda;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = Venda.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@DependsOn("Produto")
public class Venda {

    public static final String TABLE_NAME = "Venda";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    private Double totalVenda;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataVenda;

    private EStatusVenda statusVenda;

    private EMetodoPagamento metodoPagamento;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Cliente cliente;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "venda_produto", joinColumns = @JoinColumn(name = "venda_id"), inverseJoinColumns = @JoinColumn(name = "produto_id"))
    private List<Produto> produtos;

}
