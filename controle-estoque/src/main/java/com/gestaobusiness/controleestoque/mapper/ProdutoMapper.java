package com.gestaobusiness.controleestoque.mapper;

import com.gestaobusiness.controleestoque.dtos.ProdutoDTO;
import com.gestaobusiness.controleestoque.models.Produto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProdutoMapper {
    public static Produto toModel(ProdutoDTO produto) {
        Produto produtoModel = new Produto();
        produtoModel.setId(produto.getId());
        produtoModel.setNome(produto.getNome());
        produtoModel.setPreco(produto.getPreco());
        produtoModel.setImg(produto.getImg());
        return produtoModel;
    }

    public static ProdutoDTO toDTO(Produto produto, Integer quantidade) {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setId(produto.getId());
        produtoDTO.setNome(produto.getNome());
        produtoDTO.setImg(produto.getImg());
        produtoDTO.setQuantidade(quantidade);
        produtoDTO.setCategoria_Produto(produto.getCategoria_Produto());
        produtoDTO.setPreco(produto.getPreco());
        produtoDTO.setSubtotal(produto.getPreco() * quantidade);
        return produtoDTO;
    }

}
