package com.salman.ecommerce.dto;

import com.salman.ecommerce.entity.ProductCategory;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequestDto {

    private String productName;
    private double price;
    private String description;
    private String manufacturer;
    private int quantity;

    @NotNull
    private ProductCategory category;

    private String status;
}
