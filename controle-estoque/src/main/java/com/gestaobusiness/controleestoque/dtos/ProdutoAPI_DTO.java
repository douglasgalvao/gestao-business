package com.gestaobusiness.controleestoque.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProdutoAPI_DTO {

    private String description;
    private String gtin;
    private String price;
    private String avg_price;
    private String max_price;
    private String min_price; 
    private String barcode_image;
    private String thumbnail;

}
