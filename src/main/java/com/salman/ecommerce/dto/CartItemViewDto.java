package com.salman.ecommerce.dto;

import lombok.Data;

@Data
public class CartItemViewDto {

    private long cartItemId;
    private int quantity;
    private ProductViewDto product;
}
