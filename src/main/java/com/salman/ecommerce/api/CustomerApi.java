package com.salman.ecommerce.api;


import com.salman.ecommerce.dto.*;
import com.salman.ecommerce.entity.AddressType;
import com.salman.ecommerce.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Validated
public class CustomerApi {

    private final CustomerService customerService;

    @GetMapping("/current")
    @PreAuthorize("hasRole('CUSTOMER')")
    public CustomerDto getCurrentCustomer() {
        return customerService.getCurrentCustomer();
    }

    @GetMapping("/{customerId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN')")
    public CustomerDto getCustomer(@PathVariable("customerId") Long customerId) {
        return customerService.getCustomerById(customerId);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN')")
    public CustomersResponseDto getCustomers() {
        return customerService.getCustomers();
    }

    @PutMapping("/current")
    @PreAuthorize("hasRole('CUSTOMER')")
    public CustomerDto updateCurrentCustomer(@Valid @RequestBody CustomerRequestDto customerRequestDto) {
        return customerService.updateCurrentCustomer(customerRequestDto);
    }

    @PutMapping("/current/password")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseDto updateCurrentCustomerPassword(@Valid @RequestBody ChangePasswordRequestDto changePasswordRequestDto) {
        return customerService.updateCurrentCustomerPassword(changePasswordRequestDto);
    }

    @PutMapping("/current/address/{type}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public AddressDto updateCurrentCustomerAddress(@PathVariable("type") AddressType type,
                                                   @Valid @RequestBody AddressRequestDto addressRequestDto) {
        return customerService.updateCurrentCustomerAddress(type, addressRequestDto);
    }

    @PutMapping("/current/creditCard/{creditCardId}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public CreditCardDto updateCurrentCustomerCreditCard(@PathVariable("creditCardId") long creditCardId,
                                                         @Valid @RequestBody CreditCardRequestDto creditCardRequestDto) {
        return customerService.updateCurrentCustomerCreditCard(creditCardId, creditCardRequestDto);
    }

    @DeleteMapping("/current/address/{type}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseDto deleteCurrentCustomerAddress(@PathVariable("type") AddressType type) {
        return customerService.deleteCurrentCustomerAddress(type);
    }

    @DeleteMapping("/current/creditCard/{creditCardId}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseDto deleteCurrentCustomerCreditCard(@PathVariable("creditCardId") long creditCardId) {
        return customerService.deleteCurrentCustomerCreditCard(creditCardId);
    }

    @DeleteMapping("/{customerId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN')")
    public ResponseDto deleteCustomer(@PathVariable("customerId") long customerId) {
        return customerService.deleteCustomerById(customerId);
    }
}
