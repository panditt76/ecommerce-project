package com.salman.ecommerce.service;

import com.salman.ecommerce.dto.*;

import java.time.LocalDate;

public interface OrderService {

    OrdersResponseDto getOrders(LocalDate startDate);
    OrdersResponseDto getOrdersOfCurrentCustomer(LocalDate startDate);
    OrderDto getOrderById(long orderId);
    OrderDto addOrder(AddOrderRequestDto addOrderRequestDto);
    OrderDto updateOrder(long orderId, OrderRequestDto orderRequestDto);
    ResponseDto deleteOrderById(long orderId);
}
