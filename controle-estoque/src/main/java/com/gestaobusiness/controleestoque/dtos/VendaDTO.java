package com.gestaobusiness.controleestoque.dtos;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gestaobusiness.controleestoque.enums.EMetodoPagamento;
import com.gestaobusiness.controleestoque.enums.EStatusVenda;
import com.gestaobusiness.controleestoque.models.Categoria;
import com.gestaobusiness.controleestoque.models.Cliente;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendaDTO {

    private Long id;

    private Cliente cliente;

    private Double totalVenda;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataVenda;

    private EStatusVenda statusVenda;

    private EMetodoPagamento metodoPagamento;

    private List<ProdutoDTO> produtos;

    public Double getTotalVenda() {
        if (this.totalVenda == null) {
            return null;
        }

        DecimalFormat df = new DecimalFormat("#0.00");
        String formattedTotal = df.format(totalVenda);
        this.totalVenda = Double.parseDouble(formattedTotal.replace(",", "."));
        return this.totalVenda;
    }

}
