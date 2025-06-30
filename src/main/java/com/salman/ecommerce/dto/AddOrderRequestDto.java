package com.salman.ecommerce.dto;

import lombok.Data;

@Data
public class AddOrderRequestDto {

    private long cartId;
    private long creditCardId;
    private long addressId;
}
