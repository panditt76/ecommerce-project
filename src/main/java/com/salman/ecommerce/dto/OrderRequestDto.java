package com.salman.ecommerce.dto;

import com.salman.ecommerce.entity.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderRequestDto {

    @NotNull
    private OrderStatus orderStatus;
}
