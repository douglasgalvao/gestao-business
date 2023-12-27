package com.gestaobusiness.controleestoque.models;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = CodigoBarrasProduto.TABLE_NAME)
@NoArgsConstructor
@Getter
@Setter
public class CodigoBarrasProduto {

    public static final String TABLE_NAME = "CodigoBarrasProduto";

    @Id
    @jakarta.validation.constraints.Size(min = 8, max = 13, message = "O id deveter entr 8 a 13 caracteres")
    @Pattern(regexp = "\\d+", message = "O código de barras deve conter apenasnúmeros")
    @Column(name = "produtoCodBarras", length = 13, unique = true, nullable = false)
    private String id_codBarras;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "estoque_id")
    private Estoque estoque;

    @Column(name = "vendido", nullable = false, columnDefinition = "boolean default false")
    private boolean vendido;

}
