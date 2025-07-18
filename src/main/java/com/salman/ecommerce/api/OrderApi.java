package com.salman.ecommerce.api;

import com.salman.ecommerce.dto.*;
import com.salman.ecommerce.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Validated
public class OrderApi {

    private final OrderService orderService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN')")
    public OrdersResponseDto getOrders(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                           @RequestParam(value = "startDate", required = false)
                                           LocalDate startDate) {
        return orderService.getOrders(startDate);
    }

    @GetMapping("/current/customer")
    @PreAuthorize("hasRole('CUSTOMER')")
    public OrdersResponseDto getOrdersOfCurrentCustomer(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                            @RequestParam(value = "startDate", required = false)
                                                            LocalDate startDate) {
        return orderService.getOrdersOfCurrentCustomer(startDate);
    }

    @GetMapping("/{orderId}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'SELLER', 'ADMIN', 'SUPERADMIN')")
    public OrderDto getOrderById(@PathVariable long orderId) {
        return orderService.getOrderById(orderId);
    }

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public OrderDto addOrder(@Valid @RequestBody AddOrderRequestDto addOrderRequestDto) {
        return orderService.addOrder(addOrderRequestDto);
    }

    @PutMapping("/{orderId}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'SELLER', 'ADMIN', 'SUPERADMIN')")
    public OrderDto updateOrder(@PathVariable long orderId, @Valid @RequestBody OrderRequestDto orderRequestDto) {
        return orderService.updateOrder(orderId, orderRequestDto);
    }

    @DeleteMapping("/{orderId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN')")
    public ResponseDto deleteOrderById(@PathVariable long orderId) {
        return orderService.deleteOrderById(orderId);
    }
}
